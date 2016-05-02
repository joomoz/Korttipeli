package korttipeli;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * GUI - Graafinen käyttöliittymä korttipelille.
 *
 * @author Joonas Moilanen, 2016
 */
public class Kayttoliittyma extends JFrame implements Runnable {

    private javax.swing.JButton jakoNappi;
    private javax.swing.JButton exitNappi;
    private javax.swing.JButton lisaaNappi;
    private javax.swing.JButton jaaNappi;
    private javax.swing.JLabel jLabelRaha;
    private javax.swing.JLabel jLabelPanos;
    private javax.swing.JLabel jLabelJakajanSumma;
    private javax.swing.JLabel jLabelPelaajanSumma;
    private javax.swing.JLayeredPane jakajanKortit;
    private javax.swing.JLayeredPane pelaajanKortit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JSlider panoksenSaadin;
    private javax.swing.JLabel panosnaytto;
    private javax.swing.JLabel rahanaytto;
    private javax.swing.JLabel tekstinaytto;

    /**
     * Kayttoliittyma
     *
     * @param kuuntelija välittää tapahtumat toiselle luokalle.
     */
    public Kayttoliittyma(ActionListener kuuntelija) {

        Font fontti = new java.awt.Font("Engravers MT", 1, 12);

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jakajanKortit = new javax.swing.JLayeredPane();
        jLabelJakajanSumma = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pelaajanKortit = new javax.swing.JLayeredPane();
        jLabelPelaajanSumma = new javax.swing.JLabel();
        lisaaNappi = new javax.swing.JButton();
        jaaNappi = new javax.swing.JButton();
        panoksenSaadin = new javax.swing.JSlider();
        jakoNappi = new javax.swing.JButton();
        tekstinaytto = new javax.swing.JLabel();
        jLabelRaha = new javax.swing.JLabel();
        rahanaytto = new javax.swing.JLabel();
        jLabelPanos = new javax.swing.JLabel();
        panosnaytto = new javax.swing.JLabel();
        exitNappi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blackjack");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jakaja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, fontti));

        jakajanKortit.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jakajanKortit);
        jakajanKortit.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 131, Short.MAX_VALUE)
        );

        jLabelJakajanSumma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJakajanSumma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jakajanKortit)
                        .addContainerGap())
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabelJakajanSumma, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jakajanKortit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 33, Short.MAX_VALUE)
                        .addComponent(jLabelJakajanSumma, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pelaaja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, fontti));

        pelaajanKortit.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(pelaajanKortit);
        pelaajanKortit.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
                jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
        jLayeredPane2Layout.setVerticalGroup(
                jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 131, Short.MAX_VALUE)
        );

        jLabelPelaajanSumma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPelaajanSumma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pelaajanKortit)
                        .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelPelaajanSumma, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(276, 276, 276))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pelaajanKortit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addComponent(jLabelPelaajanSumma, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addContainerGap())
        );

        lisaaNappi.setFont(fontti);
        lisaaNappi.setText("LISÄÄ");
        lisaaNappi.setActionCommand("lisää");
        lisaaNappi.addActionListener(kuuntelija);

        jaaNappi.setFont(fontti);
        jaaNappi.setText("JÄÄ");
        jaaNappi.setActionCommand("jää");
        jaaNappi.addActionListener(kuuntelija);

        jakoNappi.setFont(fontti);
        jakoNappi.setText("JAKO");
        jakoNappi.setActionCommand("jako");
        jakoNappi.addActionListener(kuuntelija);

        exitNappi.setFont(fontti);
        exitNappi.setText("exit");
        exitNappi.addActionListener(kuuntelija);

        panoksenSaadin.setFont(fontti);
        panoksenSaadin.setMajorTickSpacing(10);
        panoksenSaadin.setMinimum(10);
        panoksenSaadin.setMinorTickSpacing(5);
        panoksenSaadin.setPaintLabels(true);
        panoksenSaadin.setPaintTicks(true);
        panoksenSaadin.setSnapToTicks(true);
        panoksenSaadin.setValue(20);
        panoksenSaadin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, fontti));

        tekstinaytto.setFont(fontti);
        tekstinaytto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tekstinaytto.setText("Blackjack");
        tekstinaytto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelRaha.setFont(fontti);
        jLabelRaha.setText("Rahaa:");

        jLabelPanos.setFont(fontti);
        jLabelPanos.setText("Panos:");

        jLabelJakajanSumma.setFont(fontti);
        jLabelJakajanSumma.setText("-");

        jLabelPelaajanSumma.setFont(fontti);
        jLabelPelaajanSumma.setText("-");

        rahanaytto.setFont(fontti);
        rahanaytto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        panosnaytto.setFont(fontti);
        panosnaytto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 10, Short.MAX_VALUE)
                                        .addComponent(jakoNappi, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lisaaNappi, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jaaNappi, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panoksenSaadin, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tekstinaytto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelRaha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rahanaytto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelPanos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panosnaytto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{jakoNappi, lisaaNappi, jaaNappi});

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tekstinaytto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelRaha, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                .addComponent(rahanaytto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelPanos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panosnaytto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jakoNappi)
                                .addComponent(lisaaNappi)
                                .addComponent(jaaNappi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panoksenSaadin, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[]{jakoNappi, lisaaNappi, jaaNappi});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(exitNappi, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitNappi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    public void asetaTekstinaytto(String text) {
        tekstinaytto.setText(text);
    }

    public void asetaRahanaytto(String text) {
        rahanaytto.setText(text);
    }

    public void asetaPanosnaytto(String text) {
        panosnaytto.setText(text);
    }
    
    public void asetaJakajanKadenSumma(String text) {
        jLabelJakajanSumma.setText(text);
    }
    
    public void asetaPelaajanKadenSumma(String text) {
        jLabelPelaajanSumma.setText(text);
    }

    /**
     * Piirtää kortin käyttöliittymään.
     *
     * @param kortti Piirrettävä kortti
     * @param pelaaja ihminen/jakaja
     */
    public void piirraKortti(Pelikortti kortti, Pelaaja pelaaja) {
        ImageIcon kuva;
        JLabel cardLabel;
        switch (pelaaja.getNimi()) {
            case "jakaja":
                if (pelaaja.korttienMaara() < 2) {
                    kuva = new ImageIcon("cards/back.png");
                } else {
                    kuva = new ImageIcon(kortti.getKuva());
                }
                cardLabel = new JLabel(kuva);
                cardLabel.setBounds(180 + 40 * pelaaja.korttienMaara(), 4, kuva.getIconWidth(), kuva.getIconHeight());
                jakajanKortit.add(cardLabel, new Integer(pelaaja.korttienMaara()));
                break;
            case "ihminen":
                kuva = new ImageIcon(kortti.getKuva());
                cardLabel = new JLabel(kuva);
                cardLabel.setBounds(180 + 40 * pelaaja.korttienMaara(), 4, kuva.getIconWidth(), kuva.getIconHeight());
                pelaajanKortit.add(cardLabel, new Integer(pelaaja.korttienMaara()));
                break;
            default:
        }
    }

    /**
     * Piirtää jakajan kortit uudestaan, jotta ensimmäinenkin kortti voidaan
     * näyttää.
     *
     * @param pelaaja
     */
    public void naytaJakajanEkaKortti(Pelaaja pelaaja) {
        jakajanKortit.removeAll();
        for (int k = 0; k < 2; k++) {
            ImageIcon kuva = new ImageIcon(pelaaja.getKortti(k).getKuva());
            JLabel cardLabel = new JLabel(kuva);
            cardLabel.setBounds(180 + 40 * (k + 1), 4, kuva.getIconWidth(), kuva.getIconHeight());
            jakajanKortit.add(cardLabel, new Integer(k + 1));
        }
    }

    /**
     * Asettaa valintapainikkeet joko päälle tai pois päältä eri tilanteiden mukaan.
     * @param toiminto 
     */
    public void asetaNappuloidenTila(String toiminto) {
        switch (toiminto) {
            case "jako":
                this.jakoNappi.setEnabled(false);
                this.lisaaNappi.setEnabled(true);
                this.jaaNappi.setEnabled(true);
                this.panoksenSaadin.setEnabled(false);
                break;
            case "lisää":
                break;
            case "jää":
                this.jakoNappi.setEnabled(true);
                this.lisaaNappi.setEnabled(false);
                this.jaaNappi.setEnabled(false);
                this.panoksenSaadin.setEnabled(true);
                break;
            case "panos":
                this.jakoNappi.setEnabled(false);
                this.lisaaNappi.setEnabled(false);
                this.jaaNappi.setEnabled(false);
                this.panoksenSaadin.setEnabled(true);
                break;
            case "vainjako":
                this.jakoNappi.setEnabled(true);
                this.lisaaNappi.setEnabled(false);
                this.jaaNappi.setEnabled(false);
                this.panoksenSaadin.setEnabled(false);
                break;
            case "loppu":
                this.jakoNappi.setEnabled(false);
                this.lisaaNappi.setEnabled(false);
                this.jaaNappi.setEnabled(false);
                this.panoksenSaadin.setEnabled(false);
                break;
            case "exit":
                break;
        }
    }

    /**
     * Tyhjentää kortit näytöltä.
     */
    public void tyhjenna() {
        jakajanKortit.removeAll();
        pelaajanKortit.removeAll();
        this.repaint();
    }

    @Override
    public void run() {
        this.setVisible(true);
        this.setResizable(false);
    }
}
