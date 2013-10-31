package org.springframework.data.mongodb.authentication;

import org.springframework.data.authentication.UserCredentials;

/**
 * MongoDBUserCredentials.
 * TODO
 *
 * @author gpanthe
 * @since 31/10/13
 */
public class MongoDBUserCredentials extends UserCredentials {
    public static final MongoDBUserCredentials NO_CREDENTIALS = new MongoDBUserCredentials(null, null);

    private String authenticationDatabase;

    public MongoDBUserCredentials(String username, String password) {
        super(username, password);
    }

    public MongoDBUserCredentials(String username, String password, String authenticationDatabase) {
        super(username, password);
        this.authenticationDatabase = authenticationDatabase;
    }

    public String getAuthenticationDatabase() {
        return authenticationDatabase;
    }
}
