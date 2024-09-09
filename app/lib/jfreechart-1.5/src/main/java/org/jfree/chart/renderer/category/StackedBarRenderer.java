/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-present, by David Gilbert and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -----------------------
 * StackedBarRenderer.java
 * -----------------------
 * (C) Copyright 2000-present, by David Gilbert and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Richard Atkinson;
 *                   Thierry Saura;
 *                   Christian W. Zuckschwerdt;
 *                   Peter Kolb (patch 2511330);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.DataUtils;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;

/**
 * A stacked bar renderer for use with the {@link CategoryPlot} class.
 * The example shown here is generated by the
 * {@code StackedBarChartDemo1.java} program included in the
 * JFreeChart Demo Collection:
 * <br><br>
 * <img src="doc-files/StackedBarRendererSample.png"
 * alt="StackedBarRendererSample.png">
 */
public class StackedBarRenderer extends BarRenderer
        implements Cloneable, PublicCloneable, Serializable {

    /** For serialization. */
    static final long serialVersionUID = 6402943811500067531L;

    /** A flag that controls whether the bars display values or percentages. */
    private boolean renderAsPercentages;

    /**
     * Creates a new renderer.  By default, the renderer has no tool tip
     * generator and no URL generator.  These defaults have been chosen to
     * minimise the processing required to generate a default chart.  If you
     * require tool tips or URLs, then you can easily add the required
     * generators.
     */
    public StackedBarRenderer() {
        this(false);
    }

    /**
     * Creates a new renderer.
     *
     * @param renderAsPercentages  a flag that controls whether the data values
     *                             are rendered as percentages.
     */
    public StackedBarRenderer(boolean renderAsPercentages) {
        super();
        this.renderAsPercentages = renderAsPercentages;

        // set the default item label positions, which will only be used if
        // the user requests visible item labels...
        ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER,
                TextAnchor.CENTER);
        setDefaultPositiveItemLabelPosition(p);
        setDefaultNegativeItemLabelPosition(p);
        setPositiveItemLabelPositionFallback(null);
        setNegativeItemLabelPositionFallback(null);
    }

    /**
     * Returns {@code true} if the renderer displays each item value as
     * a percentage (so that the stacked bars add to 100%), and
     * {@code false} otherwise.
     *
     * @return A boolean.
     *
     * @see #setRenderAsPercentages(boolean)
     */
    public boolean getRenderAsPercentages() {
        return this.renderAsPercentages;
    }

    /**
     * Sets the flag that controls whether the renderer displays each item
     * value as a percentage (so that the stacked bars add to 100%), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     *
     * @param asPercentages  the flag.
     *
     * @see #getRenderAsPercentages()
     */
    public void setRenderAsPercentages(boolean asPercentages) {
        this.renderAsPercentages = asPercentages;
        fireChangeEvent();
    }

    /**
     * Returns the number of passes ({@code 3}) required by this renderer.
     * The first pass is used to draw the bar shadows, the second pass is used
     * to draw the bars, and the third pass is used to draw the item labels
     * (if visible).
     *
     * @return The number of passes required by the renderer.
     */
    @Override
    public int getPassCount() {
        return 3;
    }

    /**
     * Returns the range of values the renderer requires to display all the
     * items from the specified dataset.
     *
     * @param dataset  the dataset ({@code null} permitted).
     *
     * @return The range (or {@code null} if the dataset is empty).
     */
    @Override
    public Range findRangeBounds(CategoryDataset dataset) {
        if (dataset == null) {
            return null;
        }
        if (this.renderAsPercentages) {
            return new Range(0.0, 1.0);
        }
        else {
            return DatasetUtils.findStackedRangeBounds(dataset, getBase());
        }
    }

    /**
     * Calculates the bar width and stores it in the renderer state.
     *
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param rendererIndex  the renderer index.
     * @param state  the renderer state.
     */
    @Override
    protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea,
            int rendererIndex, CategoryItemRendererState state) {

        // calculate the bar width
        CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
        CategoryDataset data = plot.getDataset(rendererIndex);
        if (data != null) {
            PlotOrientation orientation = plot.getOrientation();
            double space = 0.0;
            if (orientation == PlotOrientation.HORIZONTAL) {
                space = dataArea.getHeight();
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                space = dataArea.getWidth();
            }
            double maxWidth = space * getMaximumBarWidth();
            int columns = data.getColumnCount();
            double categoryMargin = 0.0;
            if (columns > 1) {
                categoryMargin = xAxis.getCategoryMargin();
            }

            double used = space * (1 - xAxis.getLowerMargin()
                                     - xAxis.getUpperMargin()
                                     - categoryMargin);
            if (columns > 0) {
                state.setBarWidth(Math.min(used / columns, maxWidth));
            }
            else {
                state.setBarWidth(Math.min(used, maxWidth));
            }
        }

    }

    /**
     * Draws a stacked bar for a specific item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the plot area.
     * @param plot  the plot.
     * @param domainAxis  the domain (category) axis.
     * @param rangeAxis  the range (value) axis.
     * @param dataset  the data.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, CategoryItemRendererState state,
            Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryDataset dataset, int row,
            int column, int pass) {

        if (!isSeriesVisible(row)) {
            return;
        }

        // nothing is drawn for null values...
        Number dataValue = dataset.getValue(row, column);
        if (dataValue == null) {
            return;
        }

        double value = dataValue.doubleValue();
        double total = 0.0;  // only needed if calculating percentages
        if (this.renderAsPercentages) {
            total = DataUtils.calculateColumnTotal(dataset, column,
                    state.getVisibleSeriesArray());
            value = value / total;
        }

        PlotOrientation orientation = plot.getOrientation();
        double barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(),
                dataArea, plot.getDomainAxisEdge())
                - state.getBarWidth() / 2.0;

        double positiveBase = getBase();
        double negativeBase = positiveBase;

        for (int i = 0; i < row; i++) {
            Number v = dataset.getValue(i, column);
            if (v != null && isSeriesVisible(i)) {
                double d = v.doubleValue();
                if (this.renderAsPercentages) {
                    d = d / total;
                }
                if (d > 0) {
                    positiveBase = positiveBase + d;
                }
                else {
                    negativeBase = negativeBase + d;
                }
            }
        }

        double translatedBase;
        double translatedValue;
        boolean positive = (value > 0.0);
        boolean inverted = rangeAxis.isInverted();
        RectangleEdge barBase;
        if (orientation == PlotOrientation.HORIZONTAL) {
            if (positive && inverted || !positive && !inverted) {
                barBase = RectangleEdge.RIGHT;
            }
            else {
                barBase = RectangleEdge.LEFT;
            }
        }
        else {
            if (positive && !inverted || !positive && inverted) {
                barBase = RectangleEdge.BOTTOM;
            }
            else {
                barBase = RectangleEdge.TOP;
            }
        }

        RectangleEdge location = plot.getRangeAxisEdge();
        if (positive) {
            translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea,
                    location);
            translatedValue = rangeAxis.valueToJava2D(positiveBase + value,
                    dataArea, location);
        }
        else {
            translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea,
                    location);
            translatedValue = rangeAxis.valueToJava2D(negativeBase + value,
                    dataArea, location);
        }
        double barL0 = Math.min(translatedBase, translatedValue);
        double barLength = Math.max(Math.abs(translatedValue - translatedBase),
                getMinimumBarLength());

        Rectangle2D bar;
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new Rectangle2D.Double(barL0, barW0, barLength,
                    state.getBarWidth());
        }
        else {
            bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(),
                    barLength);
        }
        if (pass == 0) {
            if (getShadowsVisible()) {
                boolean pegToBase = (positive && (positiveBase == getBase()))
                        || (!positive && (negativeBase == getBase()));
                getBarPainter().paintBarShadow(g2, this, row, column, bar,
                        barBase, pegToBase);
            }
        }
        else if (pass == 1) {
            getBarPainter().paintBar(g2, this, row, column, bar, barBase);

            // add an item entity, if this information is being collected
            EntityCollection entities = state.getEntityCollection();
            if (entities != null) {
                addItemEntity(entities, dataset, row, column, bar);
            }
        }
        else if (pass == 2) {
            CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
                    column);
            if (generator != null && isItemLabelVisible(row, column)) {
                drawItemLabel(g2, dataset, row, column, plot, generator, bar,
                        (value < 0.0));
            }
        }
    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object ({@code null} permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StackedBarRenderer)) {
            return false;
        }
        StackedBarRenderer that = (StackedBarRenderer) obj;
        if (this.renderAsPercentages != that.renderAsPercentages) {
            return false;
        }
        return super.equals(obj);
    }

}
