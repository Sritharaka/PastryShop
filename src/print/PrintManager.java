/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class PrintManager {

    private void pageSetup(Node node, Stage owner) {

        // Create the PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job == null) {

            return;

        }

        // Show the page setup dialog
        boolean proceed = job.showPageSetupDialog(owner);

        if (proceed) {

            print(job, node);

        }

    }

    private void print(PrinterJob job, Node node) {
        // Print the node
        boolean printed = job.printPage(node);

        if (printed) {
            job.endJob();
        }
    }

}
