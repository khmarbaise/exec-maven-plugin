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

    /**
     * Will give back all windows executable extensions which are
     * currently configured.
     * @return List with the extensions. Never <code>null</code>.
     */
    List<String> getExtensions();

    /**
     * @param executable
     * @return
     */
    boolean hasExtension( String executable );

    /**
     * @param exectuable
     * @return
     */
    boolean hasExecutableExtension( String exectuable );

}