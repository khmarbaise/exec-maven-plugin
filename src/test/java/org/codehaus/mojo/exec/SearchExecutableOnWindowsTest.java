package org.codehaus.mojo.exec;

import static org.mockito.Mockito.mock;

import java.io.File;

import org.apache.maven.plugin.logging.Log;
import org.testng.annotations.Test;

public class SearchExecutableOnWindowsTest
{
    @Test( expectedExceptions = IllegalArgumentException.class )
    public void shouldThrowExceptionWithExecutableNull()
    {
        @SuppressWarnings( "unused" )
        SearchExecutableOnWindows s = new SearchExecutableOnWindows( null, null, null, null );
        //Intentionally no assertThat (...)
    }

    @Test( expectedExceptions = IllegalArgumentException.class )
    public void shouldThrowExceptionWithFileCheckerNull()
    {
        @SuppressWarnings( "unused" )
        SearchExecutableOnWindows s = new SearchExecutableOnWindows( "test", null, null, null );
        //Intentionally no assertThat (...)
    }

    @Test( expectedExceptions = IllegalArgumentException.class )
    public void shouldThrowExceptionWithLogNull()
    {
        @SuppressWarnings( "unused" )
        SearchExecutableOnWindows s = new SearchExecutableOnWindows( "test", new FileChecker( new File( "." ) ), null , null);
        //Intentionally no assertThat (...)
    }

    @Test( expectedExceptions = IllegalArgumentException.class )
    public void shouldThrowExceptionWithWindowsExectuableExtensionNull()
    {
        Log log = mock( Log.class );
        @SuppressWarnings( "unused" )
        SearchExecutableOnWindows s = new SearchExecutableOnWindows( "test", new FileChecker( new File( "." ) ), log, null);
        //Intentionally no assertThat (...)
    }
}
