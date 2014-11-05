import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TestJTextField extends JLayeredPane
{
    JPanel panel;

    public TestJTextField()
    {
        final Dimension LAYERED_PANE_SIZE = new Dimension(100, 100);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setSize(LAYERED_PANE_SIZE);
        add(panel, JLayeredPane.DEFAULT_LAYER);
        final JTextField field = new JTextField("Test");
        final JTextField field1 = new JTextField("Test1");
        final JLabel label = new JLabel("Sathish");
        // label.setBorder(new )
        panel.add(field);
        panel.add(field1);
        panel.add(label);
        final MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
        setPreferredSize(LAYERED_PANE_SIZE);
    }

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        final JFrame frame = new JFrame();
        // final JPanel panel = new JPanel();
        // panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        // final JPanel panel1 = new JPanel();
        // panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        // panel1.add(new TestJTextField());
        // panel.add(panel1);
        // final JPanel panel2 = new JPanel();
        // panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        // panel2.add(new TestJTextField());
        // panel.add(panel2);
        // frame.getContentPane().add(panel);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        // frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class MyMouseAdapter extends MouseAdapter
    {
        private JLabel dragLabel = null;

        private int dragLabelWidthDiv2;

        private int dragLabelHeightDiv2;


        @Override
        public void mousePressed(final MouseEvent me)
        {
            final JComponent comp = (JComponent) panel.getComponentAt(me.getPoint());
            // if we click on jpanel that holds a jlabel
            if (comp instanceof JLabel)
            {
                dragLabel = (JLabel) comp;

                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                final int x = me.getPoint().x - dragLabelWidthDiv2;
                final int y = me.getPoint().y - dragLabelHeightDiv2;
                dragLabel.setLocation(x, y);
                add(dragLabel, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }

        @Override
        public void mouseDragged(final MouseEvent me)
        {
            if (dragLabel == null)
            {
                return;
            }
            final int x = me.getPoint().x - dragLabelWidthDiv2;
            final int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            // repaint();
        }

        @Override
        public void mouseReleased(final MouseEvent me)
        {
        }
    }


}
