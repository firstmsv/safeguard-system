package safeguard.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Componentes {

    public static JButton botaoPrimario(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Cores.AZUL_PRIMARIO);
        btn.setForeground(Color.WHITE);
        btn.setFont(Cores.FONTE_BTN);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(Cores.AZUL_HOVER); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(Cores.AZUL_PRIMARIO); }
        });
        return btn;
    }

    public static JButton botaoSecundario(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.WHITE);
        btn.setForeground(Cores.TEXTO_PRINCIPAL);
        btn.setFont(Cores.FONTE_BTN);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new CompoundBorder(
            new LineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(7, 16, 7, 16)
        ));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(245, 245, 245)); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(Color.WHITE); }
        });
        return btn;
    }

    public static JButton botaoPerigo(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Cores.VERMELHO_CLARO);
        btn.setForeground(Cores.VERMELHO);
        btn.setFont(Cores.FONTE_BTN);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new CompoundBorder(
            new LineBorder(new Color(240, 149, 149), 1, true),
            new EmptyBorder(7, 16, 7, 16)
        ));
        return btn;
    }

    public static JTextField campo(String placeholder) {
        JTextField f = new JTextField();
        f.setFont(Cores.FONTE_CAMPO);
        f.setBorder(new CompoundBorder(
            new LineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(5, 10, 5, 10)
        ));
        f.setPreferredSize(new Dimension(0, 34));
        if (placeholder != null && !placeholder.isEmpty()) {
            f.setForeground(Cores.TEXTO_SECUNDARIO);
            f.setText(placeholder);
            f.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    if (f.getText().equals(placeholder)) { f.setText(""); f.setForeground(Cores.TEXTO_PRINCIPAL); }
                }
                public void focusLost(FocusEvent e) {
                    if (f.getText().isEmpty()) { f.setText(placeholder); f.setForeground(Cores.TEXTO_SECUNDARIO); }
                }
            });
        }
        return f;
    }

    public static JPasswordField campoSenha() {
        JPasswordField f = new JPasswordField();
        f.setFont(Cores.FONTE_CAMPO);
        f.setBorder(new CompoundBorder(
            new LineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(5, 10, 5, 10)
        ));
        f.setPreferredSize(new Dimension(0, 34));
        return f;
    }

    public static JComboBox<String> combo(String[] opcoes) {
        JComboBox<String> c = new JComboBox<>(opcoes);
        c.setFont(Cores.FONTE_CAMPO);
        c.setBackground(Color.WHITE);
        c.setBorder(new LineBorder(Cores.BORDA, 1));
        c.setPreferredSize(new Dimension(0, 34));
        return c;
    }

    public static JTextArea areaTexto(int linhas) {
        JTextArea ta = new JTextArea(linhas, 0);
        ta.setFont(Cores.FONTE_CAMPO);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBorder(new EmptyBorder(6, 10, 6, 10));
        return ta;
    }

    public static JLabel label(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(Cores.FONTE_LABEL);
        l.setForeground(Cores.TEXTO_SECUNDARIO);
        return l;
    }

    public static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(Cores.FUNDO_CARD);
        p.setBorder(new CompoundBorder(
            new LineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(16, 20, 16, 20)
        ));
        return p;
    }

    public static JPanel painelFundo() {
        JPanel p = new JPanel();
        p.setBackground(Cores.FUNDO);
        return p;
    }

    public static JScrollPane scroll(JComponent c) {
        JScrollPane sp = new JScrollPane(c);
        sp.setBorder(new LineBorder(Cores.BORDA, 1));
        sp.getViewport().setBackground(Color.WHITE);
        return sp;
    }

    public static JPanel statCard(String numero, String legenda, Color corNum) {
        JPanel p = new JPanel(new GridLayout(2, 1, 0, 2));
        p.setBackground(new Color(245, 248, 252));
        p.setBorder(new CompoundBorder(
            new LineBorder(Cores.BORDA, 1, true),
            new EmptyBorder(12, 14, 12, 14)
        ));
        JLabel num = new JLabel(numero, SwingConstants.CENTER);
        num.setFont(Cores.FONTE_STAT);
        num.setForeground(corNum);
        JLabel lbl = new JLabel(legenda, SwingConstants.CENTER);
        lbl.setFont(Cores.FONTE_STAT_LBL);
        lbl.setForeground(Cores.TEXTO_SECUNDARIO);
        p.add(num); p.add(lbl);
        return p;
    }
}
