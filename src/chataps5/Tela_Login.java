package chataps5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

public class Tela_Login extends javax.swing.JFrame {

    public static final String DISCONNECT_STRING = "DISCONNECT";
    Painel_Login loginP;
    ListadeClientes ListaAmigos;
    ger_Clientes clientManager;
    ClientStatusListener clientStatus;
    ClientListListener clientListListener;
    ClientWindowListener clientWindowListener;
    Servidor serverSettings;
    String userName;
    int messagingFrameNo = 0;
    MessagingFrame[] messagingFrames;

    public Tela_Login(ger_Clientes getClientManager) {
        clientStatus = new myClientStatus();
        clientListListener = new myClientListListener();
        clientWindowListener = new MyClientWindowListener();
        messagingFrames = new MessagingFrame[10000];
        initComponents();
        clientManager = getClientManager;
        myPanel.setLayout(new BorderLayout());
        addLogInPanel();
    }

    void addLogInPanel() {
        loginP = new Painel_Login();
        myPanel.add(loginP, BorderLayout.CENTER);
        setVisible(true);

        loginP.but_entrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                but_entrarActionPerformed();
            }
        });
    }

    void addListaAmigos() {
        ListaAmigos = new ListadeClientes();
        myPanel.add(ListaAmigos, BorderLayout.CENTER);
        setVisible(true);

        ListaAmigos.list_online_clients.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    String to = (String) ListaAmigos.list_online_clients.getSelectedValue();

                    int openWindowNo = 0;

                    boolean isWindowOpen = false;
                    for (int i = 0; i < messagingFrameNo; i++) {
                        if (messagingFrames[i].to.equalsIgnoreCase(to)) {
                            isWindowOpen = true;
                            openWindowNo = i;
                            break;
                        }
                    }
                    if (!isWindowOpen) {
                        messagingFrames[messagingFrameNo] = new MessagingFrame(to, userName, clientManager);
                        messagingFrames[messagingFrameNo].setVisible(true);
                        messagingFrameNo++;
                    }
                    if (isWindowOpen) {
                        messagingFrames[openWindowNo].setVisible(true);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        myPanel = new javax.swing.JPanel();
        lb_status = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mi_sign_in = new javax.swing.JMenuItem();
        mi_sign_out = new javax.swing.JMenuItem();
        server_ip = new javax.swing.JMenuItem();

        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Usuário");

        javax.swing.GroupLayout myPanelLayout = new javax.swing.GroupLayout(myPanel);
        myPanel.setLayout(myPanelLayout);
        myPanelLayout.setHorizontalGroup(
                myPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 282, Short.MAX_VALUE)
        );
        myPanelLayout.setVerticalGroup(
                myPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 314, Short.MAX_VALUE)
        );

        lb_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_status.setText("Favor logar no servidor.");

        jMenu1.setText("Usuário");

        mi_sign_in.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mi_sign_in.setText("Entrar");
        jMenu1.add(mi_sign_in);

        mi_sign_out.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mi_sign_out.setText("Sair");
        mi_sign_out.setEnabled(false);
        mi_sign_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_sign_outActionPerformed(evt);
            }
        });
        jMenu1.add(mi_sign_out);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Configurações de IP");
        server_ip.setText("Configurações do Servidor");
        server_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                server_ipActionPerformed(evt);
            }
        });
        jMenu3.add(server_ip);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(myPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_status, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(myPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_status)
                                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }

    private void but_entrarActionPerformed() {
        if (!loginP.tf_nome_usuario.getText().isEmpty()) {
            if (serverSettings != null) {
                String sIp = serverSettings.tf_ip.getText();
                String sPort = serverSettings.tf_port.getText();
                System.out.println("IP:" + sIp + "\n" + sPort);
                if (sIp != null && sPort != null) {
                    myPanel.remove(loginP);
                    clientManager.conectar(clientStatus, sIp, sPort);
                    addListaAmigos();
                    userName = loginP.tf_nome_usuario.getText();
                    setTitle("ChatAPS (" + userName + ")");
                    clientManager.sendMessage("login " + userName);
                    clientManager.receiveMessage(clientListListener, clientWindowListener);
                    mi_sign_in.setEnabled(false);
                    mi_sign_out.setEnabled(true);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Favor configurar o servidor!!!");
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Favor configurar o servidor!!!");
            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Favor digitar um nome válido!!!");
        }
    }

    private void mi_sign_outActionPerformed(java.awt.event.ActionEvent evt) {
        myPanel.remove(ListaAmigos);
        clientManager.sendMessage(DISCONNECT_STRING + " " + userName);
        clientManager.flageoutput = true;
        addLogInPanel();
        mi_sign_out.setEnabled(false);
        mi_sign_in.setEnabled(true);
        clientManager.desconectar(clientStatus);
    }

    private void server_ipActionPerformed(java.awt.event.ActionEvent evt) {
        serverSettings = new Servidor();
        serverSettings.setLocation(550, 100);
        serverSettings.setVisible(true);

    }

    class myClientStatus implements ClientStatusListener {

        public void loginStatus(String status) {
            lb_status.setText(status);
        }
    }

    class myClientListListener implements ClientListListener {

        public void addToList(String usersName) {
            if (!usersName.equalsIgnoreCase(userName)) {
                ListaAmigos.list_model.addElement(usersName);

            }
        }

        public void removeFromList(String userName) {
            ListaAmigos.list_model.removeElement(userName);
        }
    }

    class MyClientWindowListener implements ClientWindowListener {

        public void openWindow(String message) {
            boolean isWindowOpen = false;
            int openWindowNo = 0;

            StringTokenizer tokens = new StringTokenizer(message);
            String to = tokens.nextToken();
            String from = tokens.nextToken();
            for (int i = 0; i < messagingFrameNo; i++) {
                if (messagingFrames[i].to.equalsIgnoreCase(from)) {
                    isWindowOpen = true;
                    openWindowNo = i;
                    break;
                }
            }

            if (isWindowOpen) {
                messagingFrames[openWindowNo].ta_view_message.append(message.replaceFirst(to, "") + "\n");
                if (message.contains("[CRY]")) {
                    Emoticon.Emoticon();
                }
                messagingFrames[openWindowNo].setVisible(true);
                TocaSom.Tocar();
            } else {
                messagingFrames[messagingFrameNo] = new MessagingFrame(from, userName, clientManager);
                messagingFrames[messagingFrameNo].setVisible(true);
                messagingFrames[messagingFrameNo].ta_view_message.append(message.replaceFirst(to, "") + "\n");
                if (message.contains("[CRY]")) {
                    Emoticon.Emoticon();
                }
                TocaSom.Tocar();
                messagingFrameNo++;
            }
        }

        public void closeWindow(String getMessage) {
            myPanel.remove(ListaAmigos);
            addLogInPanel();
            mi_sign_out.setEnabled(false);
            mi_sign_in.setEnabled(true);
            lb_status.setText(getMessage);
        }

        public void fileStatus(String filesStatus) {
            lb_status.setText(filesStatus);
        }
    }

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JLabel lb_status;
    private javax.swing.JMenuItem mi_sign_in;
    private javax.swing.JMenuItem mi_sign_out;
    private javax.swing.JPanel myPanel;
    private javax.swing.JMenuItem server_ip;
}