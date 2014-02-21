package org.codehaus.mojo.exec;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

/**
 * This class factors out the things to check if a file is executable on windows or not and the path separator. This
 * separate class makes it easier to write unit tests and mock out object to control the behaviour.
 * 
 * @author <a href="mailto:khmarbaise@codehaus.org">Karl-Heinz Marbaise</a>
 */
public class FileChecker extends FileWrapper
{
    private final File directory;

    public FileChecker( File directory )
    {
        this.directory = directory;
    }

    public boolean isExecutable( String executable )
    {
        return isExecutableOnWindows( this.directory, executable );
    }

}
