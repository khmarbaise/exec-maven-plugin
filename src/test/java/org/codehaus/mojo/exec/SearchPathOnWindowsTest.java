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

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:khmarbaise@codehaus.org">Karl-Heinz Marbaise</a>
 */
public class SearchPathOnWindowsTest
{

    public class ConstructorTests
    {

        @Test( expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "enviro can't be null" )
        public void shouldThrowExceptionWithEnvironmentNull()
        {
            @SuppressWarnings( "unused" )
            SearchPathOnWindows s = new SearchPathOnWindows( null, null, null, null );
            //Intentionally no assertThat (...)
        }

        @Test( expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "The environment must contain PATH element." )
        public void shouldThrowExceptionWithEnvironmentNotContainingPath()
        {
            Map<String, String> environment = Collections.singletonMap( "Test", "/usr/local/bin" );
            @SuppressWarnings( "unused" )
            SearchPathOnWindows s = new SearchPathOnWindows( environment, null, null, null );
            //Intentionally no assertThat (...)
        }

        @Test( expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "executable can't be null" )
        public void shouldThrowExceptionWithExecutableNull()
        {
            Map<String, String> environment = Collections.singletonMap( "PATH", "/usr/local/bin" );
            @SuppressWarnings( "unused" )
            SearchPathOnWindows s = new SearchPathOnWindows( environment, null, null, null );
            //Intentionally no assertThat (...)
        }

        @Test( expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "fileChecker can't be null" )
        public void shouldThrowExceptionWithFileWrapperNull()
        {
            Map<String, String> environment = Collections.singletonMap( "PATH", "/usr/local/bin" );
            @SuppressWarnings( "unused" )
            SearchPathOnWindows s = new SearchPathOnWindows( environment, "exe", null, null );
            //Intentionally no assertThat (...)
        }

        @Test( expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "log can't be null" )
        public void shouldThrowExceptionWithLogNull()
        {
            Map<String, String> environment = Collections.singletonMap( "PATH", "/usr/local/bin" );
            @SuppressWarnings( "unused" )
            SearchPathOnWindows s = new SearchPathOnWindows( environment, "exe", new FileWrapper(), null );
            //Intentionally no assertThat (...)
        }
    }

    public class UsualTests
    {
        @Test
        public void getExecutableShouldReturnExecutable()
        {
            Map<String, String> environment = Collections.singletonMap( "PATH", "C:\\installation\\test;b;c" );
            String executable = "xyz";

            Log log = mock( Log.class );

            FileWrapper fileChecker = mock( FileChecker.class );

            when( fileChecker.getPathSeparator() ).thenReturn( ";" );
            when( fileChecker.isExecutable( anyString(), anyString() ) ).thenReturn( false );
            when( fileChecker.isExecutable( "c", "xyz" ) ).thenReturn( true );

            SearchPathOnWindows spow = new SearchPathOnWindows( environment, executable, fileChecker, log );

            assertThat( spow.getExecutable() ).isEqualTo( "xyz" );

        }

    }

}
