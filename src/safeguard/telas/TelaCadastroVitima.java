package safeguard.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaCadastroVitima extends JFrame {

    private JTextField campoNome, campoCPF, campoTelefone, campoEndereco, campoMedida;
    private JComboBox<String> comboStatus;
    private JTextArea campoObs;
    private JSpinner spinnerNasc;

    public TelaCadastroVitima() {
        setTitle("SafeGuard — Cadastro de Vítima");
        setSize(720, 580);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(TelaPrincipal.COR_CONTEUDO);

        add(criarTopBar(), BorderLayout.NORTH);

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        conteudo.setBackground(TelaPrincipal.COR_CONTEUDO);
        conteudo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de vítima");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(50, 50, 60));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        conteudo.add(titulo);
        conteudo.add(Box.createVerticalStrut(16));

        JPanel cardDados = TelaPrincipal.criarCard("Dados pessoais");
        JPanel gridDados = new JPanel(new GridLayout(3, 2, 12, 10));
        gridDados.setBackground(Color.WHITE);
        gridDados.add(criarGrupo("Nome completo *", campoNome    = criarCampo("Nome da vítima")));
        gridDados.add(criarGrupo("CPF",             campoCPF     = criarCampo("000.000.000-00")));
        gridDados.add(criarGrupo("Telefone *",      campoTelefone= criarCampo("(27) 99999-9999")));
        spinnerNasc = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerNasc, "dd/MM/yyyy");
        spinnerNasc.setEditor(dateEditor);
        spinnerNasc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gridDados.add(criarGrupoComp("Data de nascimento", spinnerNasc));
        gridDados.add(criarGrupo("Endereço *", campoEndereco = criarCampo("Rua, número, bairro — ES")));
        gridDados.add(new JPanel());
        cardDados.add(gridDados, BorderLayout.CENTER);
        conteudo.add(cardDados);
        conteudo.add(Box.createVerticalStrut(12));

        JPanel cardMP = TelaPrincipal.criarCard("Medida protetiva");
        JPanel gridMP = new JPanel(new GridLayout(2, 2, 12, 10));
        gridMP.setBackground(Color.WHITE);
        gridMP.add(criarGrupo("Número da medida protetiva", campoMedida = criarCampo("N.º do processo")));
        String[] statusOpc = {"Ativa", "Suspensa", "Encerrada"};
        comboStatus = new JComboBox<>(statusOpc);
        comboStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gridMP.add(criarGrupoComp("Status", comboStatus));
        campoObs = new JTextArea(2, 20);
        campoObs.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campoObs.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        campoObs.setLineWrap(true);
        JPanel obsPanel = new JPanel(new BorderLayout());
        obsPanel.setBackground(Color.WHITE);
        JLabel obsLabel = new JLabel("Observações");
        obsLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        obsLabel.setForeground(TelaPrincipal.COR_TEXTO_SEC);
        obsPanel.add(obsLabel, BorderLayout.NORTH);
        obsPanel.add(new JScrollPane(campoObs), BorderLayout.CENTER);
        gridMP.add(obsPanel);
        gridMP.add(new JPanel());
        cardMP.add(gridMP, BorderLayout.CENTER);
        conteudo.add(cardMP);
        conteudo.add(Box.createVerticalStrut(16));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnRow.setBackground(TelaPrincipal.COR_CONTEUDO);
        btnRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnRow.add(TelaPrincipal.criarBotaoPrimario("✔ Salvar cadastro", this::salvar));
        btnRow.add(TelaPrincipal.criarBotaoSecundario("Limpar", this::limpar));
        btnRow.add(TelaPrincipal.criarBotaoSecundario("Fechar", this::dispose));
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
        JLabel tit = new JLabel("Cadastro de vítima");
        tit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tit.setForeground(new Color(200, 220, 240));
        bar.add(tit, BorderLayout.EAST);
        return bar;
    }

    private JTextField criarCampo(String placeholder) {
        JTextField f = new JTextField(placeholder);
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
        if (campoNome.getText().trim().isEmpty() || campoTelefone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios (*).", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Vítima cadastrada com sucesso!", "SafeGuard", JOptionPane.INFORMATION_MESSAGE);
        limpar();
    }

    private void limpar() {
        campoNome.setText("");
        campoCPF.setText("");
        campoTelefone.setText("");
        campoEndereco.setText("");
        campoMedida.setText("");
        campoObs.setText("");
        comboStatus.setSelectedIndex(0);
    }
}
