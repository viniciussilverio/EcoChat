package chataps5;

import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.io.ObjectInputStream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MessagingFrame extends javax.swing.JFrame {

    ger_Clientes clientManager;
    String from,to;
    ObjectInputStream input;
    ExecutorService clientExecutor;
    public MessagingFrame(String getto,String getfrom,ger_Clientes getClientManager)
    {
        from=getfrom;
        to=getto;
        initComponents();
        setTitle(to);
        clientManager=getClientManager;
        clientExecutor=Executors.newCachedThreadPool();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_view_message = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tp_write_message = new javax.swing.JTextPane();
        but_cry = new javax.swing.JButton();
        but_sad = new javax.swing.JButton();
        but_happy = new javax.swing.JButton();
        but_angry = new javax.swing.JButton();
        but_guitarra = new javax.swing.JButton();
        but_vitas = new javax.swing.JButton();
        but_salvar = new javax.swing.JButton();
        
        but_send = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        ta_view_message.setBackground(new java.awt.Color(254, 255, 230));
        ta_view_message.setColumns(20);
        ta_view_message.setEditable(false);
        ta_view_message.setRows(5);
        ta_view_message.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jScrollPane1.setViewportView(ta_view_message);
        jScrollPane1.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
            ta_view_message.select(ta_view_message.getHeight()+1000,0);
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tp_write_message.setBorder(null);
        jScrollPane2.setViewportView(tp_write_message);

        but_send.setText("Enviar");
        but_send.addActionListener((java.awt.event.ActionEvent evt) -> {
            but_sendActionPerformed(evt);
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(but_send)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(but_send, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        //Área de Emoticons
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Emoticons", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        
         
        //Botões de Emoticon
        
        but_cry.setIcon(new ImageIcon(Emoticon.class.getResource("/media/crying-1.png")));
        but_cry.addActionListener((java.awt.event.ActionEvent evt) -> {
            but_emoticonActionPerformed(evt, "[;(]");
        });

        but_angry.setIcon(new ImageIcon(Emoticon.class.getResource("/media/angry-1.png")));
        but_angry.addActionListener((java.awt.event.ActionEvent evt) -> {
            but_emoticonActionPerformed(evt, "[:*]");
        });

        but_happy.setIcon(new ImageIcon(Emoticon.class.getResource("/media/smile-1.png")));
        but_happy.addActionListener((java.awt.event.ActionEvent evt) -> {
            but_emoticonActionPerformed(evt, "[:)]");
        });

        but_sad.setIcon(new ImageIcon(Emoticon.class.getResource("/media/sceptic-1.png")));
        but_sad.addActionListener((java.awt.event.ActionEvent evt) -> {
            but_emoticonActionPerformed(evt, "[:(]");
        });
        

        jPanel3.setLayout(new GridLayout(0,4));
        jPanel3.add(but_cry);
        jPanel3.add(but_happy);
        jPanel3.add(but_sad);
        jPanel3.add(but_angry);
        
        
        //Área de Sons
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sons e mais", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N
        
        //Botões de Sons
        
        but_guitarra.setText("Guitarra");
        but_guitarra.addActionListener((java.awt.event.ActionEvent evt) -> {
            but_emoticonActionPerformed(evt, "[3]");
        });

        but_vitas.setText("Vitas");
        but_vitas.addActionListener((java.awt.event.ActionEvent evt) -> {
            but_emoticonActionPerformed(evt, "[2]");
        });
        
        but_salvar.setText("Salvar");
        but_salvar.addActionListener((java.awt.event.ActionEvent evt) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de Texto","txt"));
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try{
                    Arquivo.gravarArquivo(ta_view_message.getText().replaceAll("\n", System.getProperty("line.separator")), fileChooser.getSelectedFile().getAbsolutePath().endsWith(".txt") ? fileChooser.getSelectedFile().getAbsolutePath() : fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                }catch (Exception e){
                    System.out.println("Erro para gravar arquivo");
                }
            }
        });   

        jPanel4.setLayout(new GridLayout(0,3));
        jPanel4.add(but_guitarra);
        jPanel4.add(but_vitas);
        jPanel4.add(but_salvar);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)	
                .addGap(10, 10, 10))
             .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()	
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        setIconImage(new ImageIcon(Emoticon.class.getResource("/media/icone-1.png")).getImage());
        pack();
    }

    private void but_sendActionPerformed(java.awt.event.ActionEvent evt) {
        sendMessage();
    }
    
    private void but_emoticonActionPerformed(java.awt.event.ActionEvent evt, String n) {
        sendEmoticon(n);
    }
    
    void sendMessage()
    {
        String message=to+" "+from+" : " + tp_write_message.getText();
        clientManager.sendMessage(message);
        tp_write_message.setText(null);
        ta_view_message.append(message.replaceFirst(to,"")+"\n");
    }
    
    void sendEmoticon(String n)
    {
        String message=to+" "+from+" : " + n;
        clientManager.sendMessage(message);
        tp_write_message.setText(null);
        ta_view_message.append(message.replaceFirst(to,"")+"\n");
    }
    
    public void receiveMessage(String message)
    {
        ta_view_message.append(message);
    }
    

    public javax.swing.JButton but_send, but_cry, but_angry, but_sad, but_happy, but_guitarra, but_vitas, but_salvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea ta_view_message;
    public javax.swing.JTextPane tp_write_message;
}
