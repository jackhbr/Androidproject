package JCombox;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class aa implements ItemListener
{
    private   JComboBox<Object> comboBox ;
    public aa()
    {
        JFrame frame = new JFrame("window");
         
        final String def[] =
        { "A", "B", "C", "D", "E" };        
        comboBox = new JComboBox<Object>(def);
        comboBox.addItemListener(this);
        comboBox.setEditable(true);
        frame.add(comboBox, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        for(int i = 0;i<comboBox.getItemCount();i++)
            System.out.println(comboBox.getItemAt(i));
         
    }
    public static void main(final String args[])
    {
        new aa();
    }
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        System.out.println(comboBox.getSelectedItem());
         
    }
}
