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
 * This class extracts some functionality which is file based task.
 * This makes it easier to mock out this during testing.
 * 
 * @author <a href="mailto:khmarbaise@codehaus.org">Karl-Heinz Marbaise</a>
 *
 */
public class FileWrapper
{

    public boolean isExecutableOnWindows( File folder, String executable )
    {
        // we should probably also test canExecute, but this is only
        // available in SDK 6.
        // With the following we keep 1.5 compatibility for the moment.
        return new File( folder, executable ).isFile();
    }

    public boolean isExecutableOnWindows( String folder, String executable )
    {
        return isExecutableOnWindows( new File( folder ), executable );
    }

    public boolean isExecutable( String folder, String executable )
    {
        return isExecutableOnWindows( folder, executable );
    }

    public String getPathSeparator()
    {
        return File.pathSeparator;
    }

}
