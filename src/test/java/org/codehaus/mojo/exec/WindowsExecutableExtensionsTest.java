package org.codehaus.mojo.exec;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WindowsExecutableExtensionsTest
{

    public class CheckExtensionsTest
    {
        @DataProvider( name ="differentExtensions")
        public Object[][] differentExtensions()
        {
            //@formatter:off
            return new Object[][] { 
                { "xyz.com", true }, 
                { "xyz.exe", true }, 
                { "xyz.bat", true }, 
                { "xyz.cmd", true }, 
                { "xyz.vbs", true }, 
                { "xyz.vbe", true }, 
                { "xyz.js", true }, 
                { "xyz.jse", true }, 
                { "xyz.wsf", true }, 
                { "xyz.wsh", true }, 
                { "xyz.abz", false }, 
            };
            //@formatter:on
        }

        @Test(dataProvider = "differentExtensions")
        public void checkForExtensions(String executable, boolean expectedResult)
        {
            WindowsExecutableExtension wee = new DefaultWindowsExecutableExtensions();
            assertThat( wee.hasExecutableExtension( executable ) ).isEqualTo( expectedResult );
        }

    }

    public class OtherTests
    {
        @Test
        public void getextension()
        {
            String t1 = "xyz.tz";
            WindowsExecutableExtension wee = new DefaultWindowsExecutableExtensions();
            assertThat( wee.getExtension( t1 ) ).isEqualTo( "tz" );
        }

    }
}
