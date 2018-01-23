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

package org.openshift.webservice;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.entity.ContentType;
import org.openshift.model.Character;

@RequestScoped
public class MongoCharacterPersistence implements PersistCharacter {
    @Override
    public String saveCharacter(Character savableCharacter) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();


        try {
            String responseString = org.apache.http.client.fluent.Request.Post("http://mongoservice-holycanoli.b9ad.pro-us-east-1.openshiftapps.com/ws/players")
                    .bodyString(gson.toJson(savableCharacter.getAllAttributes()), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();
            return responseString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
