package statistics.stock;

import java.io.*;

public class mathematicalOperations {
    String line_new = "";
    String splitBy_new = ",";
    public void Return_price(String filePath, String testFilePath, String OutputFilePath) {
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            double[] log_value_eachday_2018 = new double[23];
            double[] return_final_monthwise_2018 = new double[60];
            int log_final_counter = 0;

            double[] mean_final_2018 = new double[60];
            double[] standard_deviation_final_monthwise_2018 = new double[60];
            double[] skewness_final_monthwise_2018 = new double[60];
            double[] kurtosis_final_monthwise_2018 = new double[60];
            while ((line_new = br.readLine()) != null) {
                int counter = 0;

                for (int i = 0; i <= 22; i++) {
                    String[] month_wise_data_2018 = line_new.split(splitBy_new);// use comma as separator
                    log_value_eachday_2018[counter] = Math.log(Double.parseDouble(month_wise_data_2018[i]));
                    counter++;
                }
                double b = 0;
                double mean = 0;
                double[] daily_return_2018 = new double[22];
                double skewness_initial = 0, kurtosis_initial = 0;
                int j = 0;

                for (int i = 0; i <= log_value_eachday_2018.length - 2; i++) {
                    if (log_value_eachday_2018[i + 1] != 0 || log_value_eachday_2018[i] != 0) {
                        double a = log_value_eachday_2018[i];
                        double std_var;
                        b += (log_value_eachday_2018[i + 1] - a) / a;
                        std_var = (log_value_eachday_2018[i + 1] - a) / a;
                        mean = (mean + b);
                        daily_return_2018[j] = std_var;
                        j++;
                    } else
                        break;
                }

                double mean_final = mean / log_value_eachday_2018.length;
                return_final_monthwise_2018[log_final_counter] = b;
                mean_final_2018[log_final_counter] = mean_final;

                double standard_deviation = 0, std_dev;

                //Loop to calculate standard deviation of each month

                for (int z = 0; z <= daily_return_2018.length - 1; z++) {
                    standard_deviation += (Math.pow((daily_return_2018[z] - mean_final), 2));
                }

                std_dev = Math.sqrt(standard_deviation / standard_deviation_final_monthwise_2018.length);
                standard_deviation_final_monthwise_2018[log_final_counter] = std_dev;

                for (int z = 0; z <= daily_return_2018.length - 1; z++) {
                    skewness_initial += Math.pow((daily_return_2018[z] - mean_final), 3);
                }

                double skewness_initial_1 = Math.pow(std_dev, 3) * daily_return_2018.length;
                double skewness_final = skewness_initial / skewness_initial_1;
                skewness_final_monthwise_2018[log_final_counter] = skewness_final;

                for (int z = 0; z <= daily_return_2018.length - 1; z++) {
                    kurtosis_initial += Math.pow((daily_return_2018[z] - mean_final), 4);
                }

                double kurtosis_initial_1 = Math.pow(std_dev, 4) * daily_return_2018.length;
                double kurtosis_final = kurtosis_initial / kurtosis_initial_1;
                kurtosis_final_monthwise_2018[log_final_counter] = kurtosis_final;
                log_final_counter++;
            }


            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br2 = new BufferedReader(new FileReader(testFilePath));

            FileWriter fw = new FileWriter(OutputFilePath);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("Year" + "," + "Closing Value" + "," + "Return" + "," + "Mean" + "," + "Standard Deviation" + "," + "Skewness" + "," + "Kurtosis");
            int i = 0;
            while ((line_new = br2.readLine()) != null) {
                pw.println(line_new + "," + return_final_monthwise_2018[i] + "," + mean_final_2018[i] + "," + standard_deviation_final_monthwise_2018[i] + "," + skewness_final_monthwise_2018[i] + "," + kurtosis_final_monthwise_2018[i]);
                i++;
            }
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
