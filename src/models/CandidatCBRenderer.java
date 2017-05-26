package models;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;

public class CandidatCBRenderer implements ListCellRenderer<File> {

    @Override
    public Component getListCellRendererComponent(JList<? extends File> list, File value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value == null)
            return new JLabel("");
        return new JLabel(value.getName());
    }

}
