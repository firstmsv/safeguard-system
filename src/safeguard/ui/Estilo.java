package safeguard.ui;

import java.awt.*;

public class Estilo {
    public static final Color AZUL_PRIMARIO  = new Color(31, 78, 121);
    public static final Color AZUL_HOVER     = new Color(24, 95, 165);
    public static final Color AZUL_CLARO     = new Color(230, 241, 251);
    public static final Color VERMELHO       = new Color(162, 45, 45);
    public static final Color VERMELHO_BG    = new Color(252, 235, 235);
    public static final Color AMBER          = new Color(99, 56, 6);
    public static final Color AMBER_BG       = new Color(250, 238, 218);
    public static final Color VERDE_BG       = new Color(234, 243, 222);
    public static final Color VERDE_TEXT     = new Color(39, 80, 10);
    public static final Color CINZA_BG       = new Color(245, 245, 245);
    public static final Color CINZA_BORDA    = new Color(210, 210, 210);
    public static final Color TEXTO_PRIMARIO = new Color(30, 30, 30);
    public static final Color TEXTO_MUTED    = new Color(100, 100, 100);
    public static final Color BRANCO         = Color.WHITE;

    public static final Font FONTE_TITULO  = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONTE_LABEL   = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONTE_INPUT   = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_BTN     = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONTE_SMALL   = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONTE_SIDEBAR = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_TOPBAR  = new Font("Segoe UI", Font.BOLD, 14);

    public static javax.swing.JButton botaoPrimario(String texto) {
        javax.swing.JButton btn = new javax.swing.JButton(texto);
        btn.setBackground(AZUL_PRIMARIO);
        btn.setForeground(BRANCO);
        btn.setFont(FONTE_BTN);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 34));
        return btn;
    }

    public static javax.swing.JButton botaoSecundario(String texto) {
        javax.swing.JButton btn = new javax.swing.JButton(texto);
        btn.setBackground(BRANCO);
        btn.setForeground(TEXTO_PRIMARIO);
        btn.setFont(FONTE_BTN);
        btn.setFocusPainted(false);
        btn.setBorder(javax.swing.BorderFactory.createLineBorder(CINZA_BORDA));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(120, 34));
        return btn;
    }

    public static javax.swing.JButton botaoPerigo(String texto) {
        javax.swing.JButton btn = new javax.swing.JButton(texto);
        btn.setBackground(VERMELHO_BG);
        btn.setForeground(VERMELHO);
        btn.setFont(FONTE_BTN);
        btn.setFocusPainted(false);
        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(240, 149, 149)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 34));
        return btn;
    }

    public static javax.swing.JTextField campo(String placeholder) {
        javax.swing.JTextField f = new javax.swing.JTextField();
        f.setFont(FONTE_INPUT);
        f.setPreferredSize(new Dimension(200, 32));
        f.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(CINZA_BORDA),
            javax.swing.BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        return f;
    }

    public static javax.swing.JComboBox<String> combo(String[] opcoes) {
        javax.swing.JComboBox<String> cb = new javax.swing.JComboBox<>(opcoes);
        cb.setFont(FONTE_INPUT);
        cb.setPreferredSize(new Dimension(200, 32));
        return cb;
    }

    public static javax.swing.JLabel label(String texto) {
        javax.swing.JLabel lbl = new javax.swing.JLabel(texto);
        lbl.setFont(FONTE_LABEL);
        lbl.setForeground(TEXTO_MUTED);
        return lbl;
    }

    public static javax.swing.JPanel painelCard() {
        javax.swing.JPanel p = new javax.swing.JPanel();
        p.setBackground(BRANCO);
        p.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(CINZA_BORDA),
            javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18)
        ));
        return p;
    }
}
