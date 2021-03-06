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
package com.twosigma.beakerx.kernel.magic.command.functionality;

import com.twosigma.beakerx.kernel.KernelFunctionality;
import com.twosigma.beakerx.kernel.MessageCreatorService;
import com.twosigma.beakerx.kernel.magic.command.MagicCommandExecutionParam;
import com.twosigma.beakerx.kernel.magic.command.outcome.MagicCommandOutcomeItem;
import com.twosigma.beakerx.kernel.magic.command.outcome.MagicCommandOutput;

import static com.twosigma.beakerx.kernel.magic.command.functionality.ClasspathAddJarMagicCommandInfo.CLASSPATH_ADD_JAR;
import static com.twosigma.beakerx.kernel.magic.command.functionality.ClasspathMagicCommandInfo.CLASSPATH;
import static com.twosigma.beakerx.kernel.magic.command.functionality.MagicCommandUtils.splitPath;

public class ClasspathAddJarMagicCommand extends ClasspathMagicCommand implements ClasspathAddJarMagic {

  public ClasspathAddJarMagicCommand(KernelFunctionality kernel, MessageCreatorService messageCreatorService) {
    super(kernel, messageCreatorService);
  }

  @Override
  public String getMagicCommandName() {
    return CLASSPATH_ADD_JAR;
  }

  @Override
  public boolean matchCommand(String command) {
    String[] commandParts = MagicCommandUtils.splitPath(command);
    return commandParts.length > 2 && commandParts[0].equals(CLASSPATH) && commandParts[1].equals(ClasspathAddJarMagicCommandInfo.ADD) && commandParts[2].equals(ClasspathAddJarMagicCommandInfo.JAR);
  }

  @Override
  public MagicCommandOutcomeItem execute(MagicCommandExecutionParam param) {
    String command = param.getCommand();
    String[] split = splitPath(command);
    if (split.length != 4) {
      return new MagicCommandOutput(MagicCommandOutput.Status.ERROR, WRONG_FORMAT_MSG + CLASSPATH_ADD_JAR, messageCreatorService);
    }
    String path = split[3];
    return addJar(path);
  }

  @Override
  public MagicCommandOutcomeItem addJar(String path) {
    ErrorData errorData = isValidPath(path);

    if (errorData.hasError()) {
      return new MagicCommandOutput(MagicCommandOutput.Status.ERROR, errorData.getMessage(), messageCreatorService);
    } else {
      return handleAddedJars(path);
    }
  }


}
