package it.test.demo.repository;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import it.test.demo.model.User;

@Repository
public class UserRepository {

    private final DynamoDbTable<User> userTable;

    public UserRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.userTable = dynamoDbEnhancedClient.table("User", TableSchema.fromBean(User.class));
    }

    public void saveUser(User user) {
        userTable.putItem(user);
    }

    public User getUserByEmail(String email) {
        return userTable.getItem(r -> r.key(k -> k.partitionValue(email)));
    }
}
