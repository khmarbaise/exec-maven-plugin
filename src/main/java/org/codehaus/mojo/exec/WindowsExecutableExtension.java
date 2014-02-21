package org.codehaus.mojo.exec;

import java.util.List;

public interface WindowsExecutableExtension
{

    String ROLE = WindowsExecutableExtension.class.getName();

    /**
     * Get the extension of the given file name. like <code>test.cmd</code> will give back <code>cmd</code>
     * 
     * @param executable
     * @return The extension without trailing dot.
     */
    String getExtension( String executable );

    List<String> getExtensions();

    boolean hasExtension( String executable );

    boolean hasExecutableExtension( String exectuable );

}