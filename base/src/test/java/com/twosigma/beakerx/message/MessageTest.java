/*
 *  Copyright 2017 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.twosigma.beakerx.message;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.twosigma.beakerx.message.MessageFactoryMock.createMessage;

public class MessageTest {
  @Test
  public void createMessageWithEmptyConstructor_messageHasHeaderIsNotNull() {
    //when
    Message message = createMessage();
    //then
    Assertions.assertThat(message.getHeader()).isNotNull();
  }

  @Test
  public void initMessageWithData_messageHasDataIsNotNull() {
    //when
    Message message = createMessage();
    //then
    Assertions.assertThat(message.getIdentities()).isNotEmpty();
    Assertions.assertThat(message.getHeader()).isNotNull();
    Assertions.assertThat(message.getParentHeader()).isNotNull();
    Assertions.assertThat(message.getMetadata()).isNotNull();
    Assertions.assertThat(message.getContent()).isNotNull();
  }
}
