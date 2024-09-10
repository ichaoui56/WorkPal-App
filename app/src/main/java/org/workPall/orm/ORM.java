package org.workPall.orm;

import java.lang.reflect.Field;
import java.sql.*;

public class ORM<T> {

    private Connection connection;

    public ORM(Connection connection) {
        this.connection = connection;
    }

    public void insert(T entity) throws SQLException, IllegalAccessException {
        Class<?> clazz = entity.getClass();
        StringBuilder sql = new StringBuilder("INSERT INTO ");

        if (clazz.isAnnotationPresent(org.workPall.orm.annotations.Table.class)) {
            org.workPall.orm.annotations.Table table = clazz.getAnnotation(org.workPall.orm.annotations.Table.class);
            sql.append(table.name()).append(" (");
        }

        Field[] fields = clazz.getDeclaredFields();
        StringBuilder values = new StringBuilder("VALUES     (");

        for (Field field : fields) {
            if (field.isAnnotationPresent(org.workPall.orm.annotations.Column.class) &&
                    !"id".equals(field.getName())) {
                org.workPall.orm.annotations.Column column = field.getAnnotation(org.workPall.orm.annotations.Column.class);
                sql.append(column.name()).append(", ");
                values.append("?, ");
            }
        }

        sql.delete(sql.length() - 2, sql.length()).append(") ");
        values.delete(values.length() - 2, values.length()).append(")");

        sql.append(values.toString());

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            int index = 1;

            for (Field field : fields) {
                if (field.isAnnotationPresent(org.workPall.orm.annotations.Column.class) &&
                        !"id".equals(field.getName())) {
                    field.setAccessible(true);
                    Object value = field.get(entity);

                    if (value instanceof Enum<?>) {
                        pstmt.setObject(index++, value.toString(), Types.VARCHAR);
                    } else {
                        pstmt.setObject(index++, value);
                    }
                }
            }
            pstmt.executeUpdate();
        }
    }

    public boolean userExists(Class<T> clazz, String email, String password) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT 1 FROM ");

        if (clazz.isAnnotationPresent(org.workPall.orm.annotations.Table.class)) {
            org.workPall.orm.annotations.Table table = clazz.getAnnotation(org.workPall.orm.annotations.Table.class);
            sql.append(table.name());
        }

        sql.append(" WHERE email = ? AND password = ?");

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            return pstmt.executeQuery().next();
        }
    }

    public T findByKey(Class<T> clazz, String keyColumn, String keyValue) throws SQLException, InstantiationException, IllegalAccessException {
        StringBuilder sql = new StringBuilder("SELECT * FROM");

        if (clazz.isAnnotationPresent(org.workPall.orm.annotations.Table.class)) {
            org.workPall.orm.annotations.Table table = clazz.getAnnotation(org.workPall.orm.annotations.Table.class);
            sql.append(table.name());
        }

        sql.append(" WHERE ").append(keyColumn).append(" = ?");

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            pstmt.setString(1, keyValue);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    T instance = clazz.newInstance();
                    Field[] fields = clazz.getDeclaredFields();

                    for (Field field : fields) {
                        if (field.isAnnotationPresent(org.workPall.orm.annotations.Column.class)) {
                            org.workPall.orm.annotations.Column column = field.getAnnotation(org.workPall.orm.annotations.Column.class);
                            Object value = rs.getObject(column.name());
                            field.setAccessible(true);
                            field.set(instance, value);
                        }
                    }

                    return instance;
                } else {
                    return null;
                }
            }
        }
    }

}
