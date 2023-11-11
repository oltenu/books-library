package repository.book;

import database.DatabaseConnectionFactory;
import helper.Id;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AbstractRepository<T> {
    private final Class<T> type;
    private final Connection connection;

    public AbstractRepository(Class<T> classType){
        this.connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();
        this.type = classType;
    }

    public AbstractRepository(Connection connection, Class<T> classType) {
        this.connection = connection;
        this.type = classType;
    }

    public List<T> findAll() {
        Statement statement;
        ResultSet resultSet;
        String query = "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Optional<T> findById(Long id) {
        PreparedStatement statement;
        ResultSet resultSet;
        String query = "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName() +
                " WHERE id = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            List<T> list = createObjects(resultSet);
            if (list.isEmpty()) {
                return Optional.empty();
            }

            return Optional.ofNullable(list.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean save(T t) {
        PreparedStatement statement;
        try {
            StringBuilder fieldsString = new StringBuilder("(");
            StringBuilder valuesString = new StringBuilder("('");
            Field[] fields = type.getDeclaredFields();

            for (int i = 0; i < fields.length - 1; i++) {
                fields[i].setAccessible(true);
                if(!fields[i].isAnnotationPresent(Id.class)){
                    fieldsString.append(fields[i].getName()).append(", ");
                    valuesString.append(fields[i].get(t)).append("', '");
                }
            }
            fields[fields.length - 1].setAccessible(true);
            if(!fields[fields.length - 1].isAnnotationPresent(Id.class)){
                fieldsString.append(fields[fields.length - 1].getName()).append(") ");
                valuesString.append(fields[fields.length - 1].get(t)).append("') ");
            }

            String query = "INSERT INTO " +
                    type.getSimpleName() +
                    fieldsString +
                    " VALUES " +
                    valuesString;

            System.out.println(query);

            statement = connection.prepareStatement(query);
            int rowInserted = statement.executeUpdate();

            return rowInserted == 1;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeAll() {
        Statement statement;
        String query = "TRUNCATE TABLE "
                + type.getSimpleName();

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> objects = new ArrayList<>();
        Constructor<T> constructor = null;

        try {
            constructor = type.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                Objects.requireNonNull(constructor).setAccessible(true);
                T instance = constructor.newInstance();

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    if(field.getType() == LocalDate.class){
                        setMethod.invoke(instance, ((LocalDateTime)value).toLocalDate());
                    }else {
                        setMethod.invoke(instance, value);
                    }
                }

                objects.add(instance);
            }
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 IntrospectionException e) {
            e.printStackTrace();
        }

        return objects;
    }
}
