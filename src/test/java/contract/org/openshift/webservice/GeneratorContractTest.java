/*
 * JBoss, Home of Professional Open Source
 * Copyright 2018, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package contract.org.openshift.webservice;

import io.restassured.builder.RequestSpecBuilder;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class GeneratorContractTest {
    private final String SERVICE_URI = "http://ddgenerator-holycanoli.b9ad.pro-us-east-1.openshiftapps.com";

    @Test
    public void assertCreatingNamedCharacter() {
        final RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(SERVICE_URI)
                .setBasePath("/ws/char")
                .build();

        given()
            .spec(builder.build())
        .when()
            .get("/jason")
            .then()
            .assertThat().body("name", equalTo("jason"));
    }

    @Test
    public void assertCreatingCharacter() {
        final RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(SERVICE_URI)
                .setBasePath("/ws/char")
                .build();

        given()
            .spec(builder.build())
        .when()
            .get()
        .then()
            .assertThat().body("loc", greaterThanOrEqualTo(0),
            "constitution", greaterThanOrEqualTo(0),
            "strength", greaterThanOrEqualTo(0),
            "race", isA(String.class),
            "charisma", greaterThanOrEqualTo(0),
            "wisdom", greaterThanOrEqualTo(0),
            "intelligence", greaterThanOrEqualTo(0),
            "dexterity", greaterThanOrEqualTo(0),
            "hitpoints", greaterThanOrEqualTo(0),
            "name", isA(String.class),
            "goldpieces", greaterThanOrEqualTo(0),
            "playerclass", isA(String.class));
    }
    @Test
    public void assertCreatingCharacterWhichIsPersisted() {
        final RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(SERVICE_URI)
                .setBasePath("/ws/char")
                .build();

        given()
            .spec(builder.build())
        .when()
            .get("/dd")
        .then()
            .assertThat().body("loc", greaterThanOrEqualTo(0),
            "constitution", greaterThanOrEqualTo(0),
            "strength", greaterThanOrEqualTo(0),
            "race", isA(String.class),
            "charisma", greaterThanOrEqualTo(0),
            "wisdom", greaterThanOrEqualTo(0),
            "intelligence", greaterThanOrEqualTo(0),
            "dexterity", greaterThanOrEqualTo(0),
            "hitpoints", greaterThanOrEqualTo(0),
            "name", isA(String.class),
            "goldpieces", greaterThanOrEqualTo(0),
            "id", isA(String.class),
            "playerclass", isA(String.class));
    }
}
