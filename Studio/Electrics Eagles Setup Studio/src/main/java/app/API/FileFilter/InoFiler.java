package app.API.FileFilter;

import java.io.File;
import java.io.FileFilter;

public class InoFiler implements FileFilter {
    private final String[] okFileExtensions = new String[] {"ino", "pde"};

    public boolean accept(File file)
    {
        for (String extension : okFileExtensions)
        {
            if (file.getName().toLowerCase().endsWith(extension))
            {
                return true;
            }
        }
        return false;
    }
}
