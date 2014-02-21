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

import org.apache.maven.plugin.logging.Log;

/**
 * @author <a href="mailto:khmarbaise@codehaus.org">Karl-Heinz Marbaise</a>
 */
public class SearchExecutableOnWindows
{

    private String executable;

    private FileChecker fileChecker;

    private Log log;

    private WindowsExecutableExtension wee;

    public SearchExecutableOnWindows( String executable, FileChecker fileChecker, Log log,
                                      WindowsExecutableExtension wee )
    {
        if ( executable == null )
        {
            throw new IllegalArgumentException( "executable can't be null" );
        }
        if ( fileChecker == null )
        {
            throw new IllegalArgumentException( "fileChecker can't be null" );
        }
        if ( log == null )
        {
            throw new IllegalArgumentException( "log can't be null" );
        }

        if ( wee == null )
        {
            throw new IllegalArgumentException( "WindowsExecutableExtensions can't be null" );
        }

        this.executable = executable;
        this.fileChecker = fileChecker;
        this.log = log;
        this.wee = wee;
    }

    public String getExecutable()
    {
        String result = null;
        if ( wee.hasExtension( executable ) )
        {
            log.debug( "executable has no extension." );
            if ( fileChecker.isExecutable( executable ) )
            {
                log.debug( "is executable." );
                result = executable;
            }
        }
        else
        {
            log.debug( "executable has extension." );

            for ( String extensions : wee.getExtensions() )
            {
                String fileToTest = executable + "." + extensions;
                if ( fileChecker.isExecutable( fileToTest ) )
                {
                    log.debug( "found executable " + executable + " with extension: " + extensions );
                    result = fileToTest;
                }

            }
        }

        return result;
    }

}
