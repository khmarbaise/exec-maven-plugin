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

import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.util.StringUtils;

/**
 * This class factors out the search within the path.
 * 
 * @author <a href="mailto:khmarbaise@codehaus.org">Karl-Heinz Marbaise</a>
 */
public class SearchPathOnWindows
{
    private Log log;

    private Map<String, String> enviro;

    private String executable;

    private FileWrapper fileChecker;

    public SearchPathOnWindows( Map<String, String> enviro, String executable, FileWrapper fileChecker, Log log )
    {
        if ( enviro == null )
        {
            throw new IllegalArgumentException( "enviro can't be null" );
        }

        if ( !enviro.containsKey( "PATH" ) )
        {
            throw new IllegalArgumentException( "The environment must contain PATH element." );
        }

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
        this.enviro = enviro;
        this.executable = executable;
        this.fileChecker = fileChecker;
        this.log = log;
    }

    public String getExecutable()
    {
        String result = null;

        String path = enviro.get( "PATH" );
        if ( path != null )
        {
            String[] elements = StringUtils.split( path, fileChecker.getPathSeparator() );
            for ( String pathItem : elements )
            {
                if ( fileChecker.isExecutable( pathItem, executable ) )
                {
                    log.debug( "Found pathItem: " + pathItem + " executable:" + executable );
                    result = executable;
                    break;
                }
            }
        }

        return result;
    }
}
