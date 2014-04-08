package org.codehaus.mojo.exec;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.logging.AbstractLogEnabled;

@Component( role = WindowsExecutableExtension.class )
public class DefaultWindowsExecutableExtensions
    extends AbstractLogEnabled
    implements WindowsExecutableExtension
{
    private List<String> extensions;

    /**
     * This is a list of possible executable extensions on Windows.
     */
    //@formatter:off
    private static final String[] WINDOWS_EXTENSIONS = { 
        "com",
        "exe", 
        "bat", 
        "cmd", 
        "vbs", 
        "vbe", 
        "js",
        "jse",
        "wsf", 
        "wsh", 
    };
    //@formatter:on

    public DefaultWindowsExecutableExtensions()
    {
        this.extensions = Arrays.asList( WINDOWS_EXTENSIONS );
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.mojo.exec.IWindowsExecutableExtension#getExtension(java.lang.String)
     */
    public String getExtension( String executable )
    {
        //TODO: Enhance to check if extension exists or not ?
        String[] result = executable.split( "\\." );
        return result[1];
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.mojo.exec.IWindowsExecutableExtension#getExtensions()
     */
    public List<String> getExtensions()
    {
        return this.extensions;
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.mojo.exec.IWindowsExecutableExtension#hasExtension(java.lang.String)
     */
    public boolean hasExtension( String executable )
    {
        //TODO: There might be better alternatives. What about commons-io ? Or plexus-utils ?  
        return executable.contains( "." );
    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.mojo.exec.IWindowsExecutableExtension#hasExecutableExtension(java.lang.String)
     */
    public boolean hasExecutableExtension( String exectuable )
    {
        boolean result = false;
        String exec = exectuable.toLowerCase( Locale.getDefault() );
        if ( hasExtension( exec ) )
        {
            String extension = getExtension( exec );
            if ( this.extensions.contains( extension ) )
            {
                result = true;
            }
        }
        return result;
    }
}
