package com.harrydrummond.is.core.util;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

public class FileUtil {

    public static File getFile(final File workingDir, final String name) {
        return new File(checkNotNull(workingDir),name);
    }

    public static File getSchematicFile(final File workingDir, final String name) {
        if (!name.endsWith(".schem")) {
            return null;
        }
        return getFile(workingDir,name);
    }
}