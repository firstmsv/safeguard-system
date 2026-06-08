package safeguard.telas;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TelaAlerta extends JFrame {

    private JComboBox<String> comboOcorrencia, comboTipo;
    private JTextArea campoMensagem;

    public TelaAlerta() {
        setTitle("SafeGuard — Alertas");
        setSize(720, 580);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(TelaPrincipal.COR_CONTEUDO);

        add(criarTopBar(), BorderLayout.NORTH);

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        conteudo.setBackground(TelaPrincipal.COR_CONTEUDO);
        conteudo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Emissão de alertas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(50, 50, 60));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        conteudo.add(titulo);
        conteudo.add(Box.createVerticalStrut(16));

        JPanel cardNovo = TelaPrincipal.criarCard("Novo alerta");
        JPanel gridN = new JPanel(new GridLayout(2, 2, 12, 10));
        gridN.setBackground(Color.WHITE);
        comboOcorrencia = new JComboBox<>(new String[]{
            "— selecione —",
            "#005 — Ana Paula S. (04/06)",
            "#004 — Carla M. (02/06)"
        });
        comboTipo = new JComboBox<>(new String[]{"Emergência", "Aviso", "Monitoramento"});
        comboOcorrencia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gridN.add(criarGrupoComp("Ocorrência vinculada *", comboOcorrencia));
        gridN.add(criarGrupoComp("Tipo de alerta *", comboTipo));
        campoMensagem = new JTextArea(3, 20);
        campoMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campoMensagem.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        campoMensagem.setLineWrap(true);
        JPanel msgPainel = new JPanel(new BorderLayout(0, 4));
        msgPainel.setBackground(Color.WHITE);
        JLabel msgLbl = new JLabel("Mensagem do alerta *");
        msgLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        msgLbl.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        msgPainel.add(msgLbl, BorderLayout.NORTH);
        msgPainel.add(new JScrollPane(campoMensagem), BorderLayout.CENTER);
        gridN.add(msgPainel);
        gridN.add(new JPanel());
        cardNovo.add(gridN, BorderLayout.CENTER);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnRow.setBackground(Color.WHITE);
        btnRow.setBorder(new EmptyBorder(12, 0, 0, 0));
        btnRow.add(TelaPrincipal.criarBotaoAlerta("🔔 Emitir alerta", this::emitir));
        btnRow.add(TelaPrincipal.criarBotaoSecundario("Cancelar", () -> {
            comboOcorrencia.setSelectedIndex(0);
            comboTipo.setSelectedIndex(0);
            campoMensagem.setText("");
        }));
        cardNovo.add(btnRow, BorderLayout.SOUTH);
        conteudo.add(cardNovo);
        conteudo.add(Box.createVerticalStrut(14));

        JPanel cardLista = TelaPrincipal.criarCard("Alertas emitidos");
        cardLista.setPreferredSize(new Dimension(0, 240));

        JPanel listaAlertas = new JPanel();
        listaAlertas.setLayout(new BoxLayout(listaAlertas, BoxLayout.Y_AXIS));
        listaAlertas.setBackground(Color.WHITE);
        listaAlertas.add(criarItemAlerta("Emergência", "#005", "Ana Paula S.", "04/06/2025 às 14:32",
            "Agressor avistado próximo à residência da vítima.", new Color(252, 235, 235), new Color(226, 75, 74), "Ativo"));
        listaAlertas.add(Box.createVerticalStrut(8));
        listaAlertas.add(criarItemAlerta("Aviso", "#004", "Carla M.", "02/06/2025 às 09:15",
            "Descumprimento de medida protetiva relatado.", new Color(250, 238, 218), new Color(186, 117, 23), "Ativo"));
        listaAlertas.add(Box.createVerticalStrut(8));
        listaAlertas.add(criarItemAlerta("Monitoramento", "#003", "Fernanda R.", "28/05/2025 às 11:00",
            "Acompanhamento rotineiro encerrado com sucesso.", new Color(230, 241, 251), new Color(24, 95, 165), "Encerrado"));

        cardLista.add(listaAlertas, BorderLayout.CENTER);
        conteudo.add(cardLista);

        add(new JScrollPane(conteudo), BorderLayout.CENTER);
    }

    private JPanel criarItemAlerta(String tipo, String id, String vitima, String dataHora, String msg, Color bgColor, Color corTipo, String status) {
        JPanel item = new JPanel(new BorderLayout(0, 4));
        item.setBackground(bgColor);
        item.setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 4, 0, 0, corTipo),
            new EmptyBorder(10, 12, 10, 12)
        ));
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        item.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(bgColor);
        JLabel lblTipo = new JLabel(tipo + " — " + id);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTipo.setForeground(corTipo.darker());
        JLabel lblStatus = new JLabel(status);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblStatus.setForeground(corTipo.darker());
        lblStatus.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(corTipo, 1),
            new EmptyBorder(1, 6, 1, 6)
        ));
        topo.add(lblTipo, BorderLayout.WEST);
        topo.add(lblStatus, BorderLayout.EAST);

        JLabel lblVitima = new JLabel("Vítima: " + vitima + " — " + dataHora);
        lblVitima.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblVitima.setForeground(corTipo.darker());

        JLabel lblMsg = new JLabel(msg);
        lblMsg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMsg.setForeground(TelaPrincipal.COR_TEXTO_SEC);

        JPanel corpo = new JPanel();
        corpo.setLayout(new BoxLayout(corpo, BoxLayout.Y_AXIS));
        corpo.setBackground(bgColor);
        corpo.add(topo);
        corpo.add(lblVitima);
        corpo.add(lblMsg);

        item.add(corpo, BorderLayout.CENTER);
        return item;
    }

    private JPanel criarTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(TelaPrincipal.COR_PRIMARIA);
        bar.setPreferredSize(new Dimension(0, 46));
        bar.setBorder(new EmptyBorder(0, 16, 0, 16));
        JLabel logo = new JLabel("🛡  SafeGuard");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        logo.setForeground(Color.WHITE);
        bar.add(logo, BorderLayout.WEST);
        JLabel tit = new JLabel("Alertas");
        tit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tit.setForeground(new Color(200, 220, 240));
        bar.add(tit, BorderLayout.EAST);
        return bar;
    }

    private JPanel criarGrupoComp(String label, JComponent comp) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        p.add(lbl, BorderLayout.NORTH);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    private void emitir() {
        if (comboOcorrencia.getSelectedIndex() == 0 || campoMensagem.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione a ocorrência e preencha a mensagem.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String tipo = (String) comboTipo.getSelectedItem();
        JOptionPane.showMessageDialog(this,
            "Alerta de " + tipo + " emitido com sucesso!\nAutoridades notificadas.",
            "SafeGuard — Alerta emitido",
            JOptionPane.WARNING_MESSAGE);
        campoMensagem.setText("");
        comboOcorrencia.setSelectedIndex(0);
    }
}
