/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.seagile.bigmart.panel;

import com.seagile.bigmart.connection.MySQL;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author DTC
 */
public class AdminDashboard extends javax.swing.JPanel {

    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() {
        initComponents();
        loadSalesChart();
        loadSalesByCategoryChart();
        loadPaymentMethodsChart();
    }

    private void loadSalesChart() {
        TimeSeries salesSeries = new TimeSeries("Sales");

        // Example sales data for 30 days
        for (int i = 1; i <= 30; i++) {
            salesSeries.add(new Day(i, 8, 2024), 300 + (int) (Math.random() * 700));
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(salesSeries);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "",
                "Date",
                "Sales (USD)",
                dataset,
                true,
                true,
                false
        );
        
        Color customeGreen = new Color(4, 170, 109);

        XYPlot plot = chart.getXYPlot();
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlinePaint(Color.GRAY);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, customeGreen);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        roundedBackground1.removeAll();
        roundedBackground1.add(chartPanel, BorderLayout.CENTER);
        roundedBackground1.validate();
    }

    private void loadSalesByCategoryChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(2000, "Sales", "Electronics");
        dataset.addValue(1500, "Sales", "Groceries");
        dataset.addValue(1200, "Sales", "Clothing");
        dataset.addValue(800, "Sales", "Toys");
        dataset.addValue(700, "Sales", "Furniture");

        JFreeChart chart = ChartFactory.createBarChart(
                "", // Chart title
                "Category", // X-Axis Label
                "Sales (USD)", // Y-Axis Label
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        BarRenderer renderer = new BarRenderer();
        renderer.setSeriesPaint(0, new Color(91, 155, 213));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setMaximumBarWidth(0.1);
        renderer.setShadowVisible(false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.DARK_GRAY);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRenderer(renderer);
        plot.setOutlineVisible(false);

        ChartPanel chartPanel = new ChartPanel(chart);
        roundedBackground3.removeAll();
        roundedBackground3.add(chartPanel, BorderLayout.CENTER);
        roundedBackground3.validate();
    }

    private void loadPaymentMethodsChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Cash", 40);
        dataset.setValue("Credit Card", 35);
        dataset.setValue("Mobile Payment", 15);
        dataset.setValue("Others", 10);

        JFreeChart chart = ChartFactory.createPieChart(
                "",
                dataset,
                true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();

        // Customizing sections with flat, vibrant colors
        plot.setSectionPaint("Cash", new Color(91, 155, 213));       // Blue
        plot.setSectionPaint("Credit Card", new Color(210, 91, 91)); // Red
        plot.setSectionPaint("Mobile Payment", new Color(91, 210, 93)); // Green
        plot.setSectionPaint("Others", new Color(249, 224, 62)); // Yellow

        // Setting a flat, clean background
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);

        Color customeGreen = new Color(4, 170, 109);

        // Style the labels
        plot.setLabelFont(new Font("Poppins", Font.PLAIN, 12));
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(customeGreen);

        ChartPanel chartPanel = new ChartPanel(chart);
        roundedBackground4.removeAll();  // Replace with your panel
        roundedBackground4.add(chartPanel, BorderLayout.CENTER);
        roundedBackground4.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedBackground2 = new com.seagile.bigmart.component.RoundedBackground();
        roundedBackground1 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        roundedBackground5 = new com.seagile.bigmart.component.RoundedBackground();
        roundedBackground3 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        roundedBackground6 = new com.seagile.bigmart.component.RoundedBackground();
        roundedBackground4 = new com.seagile.bigmart.component.RoundedBackground();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();

        roundedBackground2.setBackground(new java.awt.Color(255, 255, 255));

        roundedBackground1.setPreferredSize(new java.awt.Dimension(300, 166));
        roundedBackground1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel1.setText("Sales (Last 30 Days)");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/seagile/bigmart/img/zoom-in.png"))); // NOI18N
        jButton1.setBorderPainted(false);

        javax.swing.GroupLayout roundedBackground2Layout = new javax.swing.GroupLayout(roundedBackground2);
        roundedBackground2.setLayout(roundedBackground2Layout);
        roundedBackground2Layout.setHorizontalGroup(
            roundedBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundedBackground2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        roundedBackground2Layout.setVerticalGroup(
            roundedBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedBackground2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedBackground5.setBackground(new java.awt.Color(255, 255, 255));

        roundedBackground3.setPreferredSize(new java.awt.Dimension(100, 100));
        roundedBackground3.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel2.setText("Sales By Category");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/seagile/bigmart/img/zoom-in.png"))); // NOI18N
        jButton3.setBorderPainted(false);

        javax.swing.GroupLayout roundedBackground5Layout = new javax.swing.GroupLayout(roundedBackground5);
        roundedBackground5.setLayout(roundedBackground5Layout);
        roundedBackground5Layout.setHorizontalGroup(
            roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedBackground5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundedBackground3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        roundedBackground5Layout.setVerticalGroup(
            roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedBackground5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedBackground6.setBackground(new java.awt.Color(255, 255, 255));

        roundedBackground4.setPreferredSize(new java.awt.Dimension(100, 100));
        roundedBackground4.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel3.setText("Pyment Methods");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/seagile/bigmart/img/zoom-in.png"))); // NOI18N
        jButton2.setBorderPainted(false);

        javax.swing.GroupLayout roundedBackground6Layout = new javax.swing.GroupLayout(roundedBackground6);
        roundedBackground6.setLayout(roundedBackground6Layout);
        roundedBackground6Layout.setHorizontalGroup(
            roundedBackground6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedBackground6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedBackground6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundedBackground4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundedBackground6Layout.setVerticalGroup(
            roundedBackground6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedBackground6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedBackground6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedBackground4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundedBackground2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundedBackground5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundedBackground6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundedBackground2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedBackground5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundedBackground6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground1;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground2;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground3;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground4;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground5;
    private com.seagile.bigmart.component.RoundedBackground roundedBackground6;
    // End of variables declaration//GEN-END:variables
}
