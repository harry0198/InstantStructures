package com.harrydrummond.is.core.util;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

public class FileUtil {

    /**
     * Gets file from working directory with null checks
     * @param workingDir Data folder to check within
     * @param name Name of file
     * @return File found in data folder. Null if working directory was null.
     */
    public static File getFile(final File workingDir, final String name) {
        return new File(checkNotNull(workingDir),name);
    }

    /**
     * Returns validated File object. Checks if filename ends with .schem.
     * @param workingDir Data folder file is container within
     * @param name String name of the file.
     * @return Schematic file object if of validated file type. Null if file does not end in .schem
     */
    public static File getSchematicFile(final File workingDir, final String name) {
        if (!name.endsWith(".schem")) {
            return null;
        }
        return getFile(workingDir,name);
    }
}