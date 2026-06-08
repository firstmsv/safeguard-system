package safeguard.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaOcorrencia extends JFrame {

    private JComboBox<String> comboVitima, comboAgressor, comboTipo, comboStatus;
    private JTextField campoData, campoHora, campoLocal;
    private JTextArea campoDesc;

    public TelaOcorrencia() {
        setTitle("SafeGuard — Registro de Ocorrência");
        setSize(720, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(TelaPrincipal.COR_CONTEUDO);

        add(criarTopBar(), BorderLayout.NORTH);

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        conteudo.setBackground(TelaPrincipal.COR_CONTEUDO);
        conteudo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registro de ocorrência");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(50, 50, 60));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        conteudo.add(titulo);
        conteudo.add(Box.createVerticalStrut(16));

        JPanel cardIdent = TelaPrincipal.criarCard("Identificação");
        JPanel gridI = new JPanel(new GridLayout(3, 2, 12, 10));
        gridI.setBackground(Color.WHITE);
        comboVitima  = new JComboBox<>(new String[]{"— selecione —", "Ana Paula S.", "Carla M.", "Fernanda R."});
        comboAgressor= new JComboBox<>(new String[]{"— selecione —", "João S.", "Roberto M."});
        comboVitima.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboAgressor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campoData = criarCampo("dd/mm/aaaa");
        campoHora = criarCampo("hh:mm");
        campoLocal= criarCampo("Endereço onde ocorreu");
        gridI.add(criarGrupoComp("Vítima *", comboVitima));
        gridI.add(criarGrupoComp("Agressor *", comboAgressor));
        gridI.add(criarGrupo("Data da ocorrência *", campoData));
        gridI.add(criarGrupo("Hora", campoHora));
        gridI.add(criarGrupo("Local da ocorrência *", campoLocal));
        gridI.add(new JPanel());
        cardIdent.add(gridI, BorderLayout.CENTER);
        conteudo.add(cardIdent);
        conteudo.add(Box.createVerticalStrut(12));

        JPanel cardDet = TelaPrincipal.criarCard("Detalhamento");
        JPanel gridD = new JPanel(new GridLayout(2, 2, 12, 10));
        gridD.setBackground(Color.WHITE);
        comboTipo  = new JComboBox<>(new String[]{"Física", "Psicológica", "Sexual", "Patrimonial", "Moral"});
        comboStatus= new JComboBox<>(new String[]{"Em aberto", "Em análise", "Encerrada"});
        comboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gridD.add(criarGrupoComp("Tipo de violência *", comboTipo));
        gridD.add(criarGrupoComp("Status *", comboStatus));
        campoDesc = new JTextArea(3, 20);
        campoDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campoDesc.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        campoDesc.setLineWrap(true);
        JPanel descPainel = new JPanel(new BorderLayout(0, 4));
        descPainel.setBackground(Color.WHITE);
        JLabel descLbl = new JLabel("Descrição detalhada *");
        descLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        descLbl.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        descPainel.add(descLbl, BorderLayout.NORTH);
        descPainel.add(new JScrollPane(campoDesc), BorderLayout.CENTER);
        gridD.add(descPainel);
        gridD.add(new JPanel());
        cardDet.add(gridD, BorderLayout.CENTER);
        conteudo.add(cardDet);
        conteudo.add(Box.createVerticalStrut(16));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnRow.setBackground(TelaPrincipal.COR_CONTEUDO);
        btnRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRow.add(TelaPrincipal.criarBotaoPrimario("✔ Registrar ocorrência", this::salvar));
        btnRow.add(TelaPrincipal.criarBotaoAlerta("🔔 Emitir alerta", () -> {
            dispose();
            new TelaAlerta().setVisible(true);
        }));
        btnRow.add(TelaPrincipal.criarBotaoSecundario("Cancelar", this::dispose));
        conteudo.add(btnRow);

        add(new JScrollPane(conteudo), BorderLayout.CENTER);
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
        JLabel tit = new JLabel("Registro de ocorrência");
        tit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tit.setForeground(new Color(200, 220, 240));
        bar.add(tit, BorderLayout.EAST);
        return bar;
    }

    private JTextField criarCampo(String ph) {
        JTextField f = new JTextField(ph);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(4, 8, 4, 8)));
        return f;
    }

    private JPanel criarGrupo(String label, JTextField campo) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        p.add(lbl, BorderLayout.NORTH);
        p.add(campo, BorderLayout.CENTER);
        return p;
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

    private void salvar() {
        if (comboVitima.getSelectedIndex() == 0 || comboAgressor.getSelectedIndex() == 0 || campoDesc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios (*).", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Ocorrência registrada com sucesso!", "SafeGuard", JOptionPane.INFORMATION_MESSAGE);
    }
}
