/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb.core;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.authentication.MongoDBUserCredentials;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.Assert;

/**
 * Mongo server administration exposed via JMX annotations
 * 
 * @author Mark Pollack
 */
@ManagedResource(description = "Mongo Admin Operations")
public class MongoAdmin implements MongoAdminOperations {

	private final Mongo mongo;
	private String username;
	private String password;
    private String authenticationDatabase;

	public MongoAdmin(Mongo mongo) {
		Assert.notNull(mongo);
		this.mongo = mongo;
	}

	/* (non-Javadoc)
	  * @see org.springframework.data.mongodb.core.core.MongoAdminOperations#dropDatabase(java.lang.String)
	  */
	@ManagedOperation
	public void dropDatabase(String databaseName) {
		getDB(databaseName).dropDatabase();
	}

	/* (non-Javadoc)
	  * @see org.springframework.data.mongodb.core.core.MongoAdminOperations#createDatabase(java.lang.String)
	  */
	@ManagedOperation
	public void createDatabase(String databaseName) {
		getDB(databaseName);
	}

	/* (non-Javadoc)
	  * @see org.springframework.data.mongodb.core.core.MongoAdminOperations#getDatabaseStats(java.lang.String)
	  */
	@ManagedOperation
	public String getDatabaseStats(String databaseName) {
		return getDB(databaseName).getStats().toString();
	}

	/**
	 * Sets the username to use to connect to the Mongo database
	 * 
	 * @param username The username to use
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the password to use to authenticate with the Mongo database.
	 * 
	 * @param password The password to use
	 */
	public void setPassword(String password) {
		this.password = password;
	}

    /**
     * Sets the database to authenticate to.
     * @param authenticationDatabase The authentication database name
     */
    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }

    DB getDB(String databaseName) {
		return MongoDbUtils.getDB(mongo, databaseName, new MongoDBUserCredentials(username, password, authenticationDatabase));
	}
}
