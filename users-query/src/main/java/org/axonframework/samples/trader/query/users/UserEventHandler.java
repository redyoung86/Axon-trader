/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.samples.trader.query.users;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.samples.trader.api.users.UserCreatedEvent;
import org.axonframework.samples.trader.query.users.repositories.UserQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEventHandler {

    private final UserQueryRepository userRepository;

    public UserEventHandler(UserQueryRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent event) {
        UserEntry userEntry = new UserEntry();

        userEntry.setIdentifier(event.getUserId().toString());
        userEntry.setName(event.getName());
        userEntry.setUsername(event.getUsername());
        userEntry.setPassword(event.getPassword());

        userRepository.save(userEntry);
    }
}