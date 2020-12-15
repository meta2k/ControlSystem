package gui.util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class NodeFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {

        if(file.isDirectory()) {
            return true;
        }

        String name = file.getName();
        String extension = Utils.getFileExtension(name);

        if (extension == null) {
            return false;
        }

        if(extension.equals("node")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Node database files(*.node)";
    }




}
