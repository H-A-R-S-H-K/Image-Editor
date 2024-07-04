import java.util.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;

public class ImageEditor{

//--------------------------------------------------------   SOURCE CODE : CONVERT TO GRAYSCALE   ----------------------------------------------------//
    public static BufferedImage convertToGrayScale(BufferedImage inputImage){

        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width , height , BufferedImage.TYPE_BYTE_GRAY);

        for (int i = 0 ; i < height ; i++) {

            for (int j = 0 ; j < width ; j++) {
                outputImage.setRGB(j , i , inputImage.getRGB(j , i));
            }
        }
        return outputImage;
    }
//--------------------------------------------------------   SOURCE CODE : TRANSPOSE OF IMAGE   -------------------------------------------------------//
    public static BufferedImage transposeOfTheImage(BufferedImage inputImage){

        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(height , width , BufferedImage.TYPE_3BYTE_BGR);
        
        for (int i = 0 ; i < height ; i++) {

            for (int j = 0 ; j < width ; j++) {
                outputImage.setRGB(i , j , inputImage.getRGB(j , i));
            }
        }
        return outputImage;
    }
//--------------------------------------------------------   SOURCE CODE : ROTATE CLOCKWISE IMAGE   ---------------------------------------------------//
    public static BufferedImage rotateClockwise(BufferedImage inputImage , int degree){

        degree = degree / 90;
        if (degree > 3) {
            degree = degree % 4;
        }

        if (degree == 1) {
            BufferedImage OutputImage = transposeOfTheImage(inputImage);
            BufferedImage OutputImage1 = horizontalInvert(OutputImage);
            return OutputImage1;
        }
        
        else if (degree == 2) {
            BufferedImage OutputImage = transposeOfTheImage(inputImage);
            BufferedImage OutputImage1 = horizontalInvert(OutputImage);
            BufferedImage OutputImage2 = transposeOfTheImage(OutputImage1);
            BufferedImage OutputImage3 = horizontalInvert(OutputImage2);
            return OutputImage3;
        }

        else if (degree == 3) {
            BufferedImage OutputImage = transposeOfTheImage(inputImage);
            BufferedImage OutputImage1 = horizontalInvert(OutputImage);
            BufferedImage OutputImage2 = transposeOfTheImage(OutputImage1);
            BufferedImage OutputImage3 = horizontalInvert(OutputImage2);
            BufferedImage OutputImage4 = transposeOfTheImage(OutputImage3);
            BufferedImage OutputImage5 = horizontalInvert(OutputImage4);
            return OutputImage5;
        }
        else{
            return inputImage;
        }
    }
//--------------------------------------------------------  SOURCE CODE : ROTATE ANTI - CLOCKWISE IMAGE   ---------------------------------------------------//
    public static BufferedImage rotateAntiClockwise(BufferedImage inputImage , int degree){

        degree = degree / 90;
        if (degree > 3) {
            degree = degree % 4; 
        }

        if (degree == 1) {
            BufferedImage OutputImage = transposeOfTheImage(inputImage);
            BufferedImage OutputImage1 = horizontalInvert(OutputImage);
            BufferedImage OutputImage2 = transposeOfTheImage(OutputImage1);
            BufferedImage OutputImage3 = horizontalInvert(OutputImage2);
            BufferedImage OutputImage4 = transposeOfTheImage(OutputImage3);
            BufferedImage OutputImage5 = horizontalInvert(OutputImage4);
            return OutputImage5;
        }
        
        else if (degree == 2) {
            BufferedImage OutputImage = transposeOfTheImage(inputImage);
            BufferedImage OutputImage1 = horizontalInvert(OutputImage);
            BufferedImage OutputImage2 = transposeOfTheImage(OutputImage1);
            BufferedImage OutputImage3 = horizontalInvert(OutputImage2);
            return OutputImage3;
        }

        else if (degree == 3) {
            BufferedImage OutputImage = transposeOfTheImage(inputImage);
            BufferedImage OutputImage1 = horizontalInvert(OutputImage);
            return OutputImage1;
        }
        else{
            return inputImage;
        }
    }
//--------------------------------------------------------   SOURCE CODE : HORIZONTAL INVERT IMAGE   ---------------------------------------------------//
     public static BufferedImage horizontalInvert(BufferedImage inputImage){

        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width , height , BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0 ; i < height ; i++) {

            for (int j = 0 ; j <= width / 2 ; j++) {
                Color pixel = new Color(inputImage.getRGB(j , i));
                outputImage.setRGB(j , i , inputImage.getRGB(width - j - 1 , i));
                outputImage.setRGB(width - j - 1 , i , pixel.getRGB());
            }
        }
        return outputImage;
    }
//--------------------------------------------------------   SOURCE CODE : VERTICAL INVERT IMAGE   ------------------------------------------------------//
    public static BufferedImage verticalInvert(BufferedImage inputImage){

        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width , height , BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0 ; i < width ; i++) {

            for (int j = height - 1 ; j >= height / 2 ; j--) {
                Color pixel = new Color(inputImage.getRGB(i , height - j - 1));
                outputImage.setRGB(i , height - j - 1 , inputImage.getRGB(i , j));
                outputImage.setRGB(i , j , pixel.getRGB());
            }
        }
        return outputImage;
    }
//--------------------------------------------------------   SOURCE CODE : CHANGE  BRIGHTNESS OF IMAGE   -------------------------------------------------//
    public static BufferedImage changeBrightness(BufferedImage inputImage , double increase){

        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width , height , BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0 ; i < height ; i++) {
            
            for (int j = 0 ; j < width ; j++) {
                Color pixel = new Color(inputImage.getRGB(j , i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                red += (increase * red) / 100;
                blue += (increase * blue) / 100;
                green += (increase * green) / 100;
                if (red > 255) red = 255;
                if (blue > 255) blue = 255;
                if (green > 255) green = 255;
                if (red < 0) red = 0;
                if (blue < 0) blue = 0;
                if (green < 0) green = 0;
                Color newpixel = new Color(red , green , blue);
                outputImage.setRGB(j , i , newpixel.getRGB());
            }
        }
        return outputImage;
    }
//--------------------------------------------------------------   SOURCE CODE : BLLURNESS   ----------------------------------------------------------------//
    public static BufferedImage blurring(BufferedImage inputImage , int blurrnessLevel){

        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width , height , BufferedImage.TYPE_3BYTE_BGR);

        int heightLimit = blurrnessLevel;
        int widthLimit = blurrnessLevel;
        int temp1 = 0; 
        int temp2 = 0;
        
        while (heightLimit < height) {

            while (widthLimit < width) {
                int red = 0;
                int blue = 0;
                int green = 0;

                for (int i = temp1 ; i < heightLimit ; i++) {

                    for (int j = temp2 ; j < widthLimit ; j++) {

                        Color pixel = new Color(inputImage.getRGB(j , i));
                        red += pixel.getRed();
                        green += pixel.getGreen();
                        blue += pixel.getBlue();

                    }
                }
                red = red / (blurrnessLevel * blurrnessLevel);
                green = green / (blurrnessLevel * blurrnessLevel);
                blue = blue / (blurrnessLevel * blurrnessLevel);

                Color newpixel = new Color(red , green , blue);

                for (int i = temp1 ; i < heightLimit ; i++) {

                    for (int j = temp2 ; j < widthLimit ; j++) {

                        outputImage.setRGB(j , i , newpixel.getRGB());

                    }
                }
                widthLimit += blurrnessLevel;
                temp2 += blurrnessLevel;
            }

            heightLimit += blurrnessLevel;
            temp1 += blurrnessLevel;
            widthLimit = blurrnessLevel;
            temp2 = 0;

        }
        
        return outputImage;
    }
//------------------------------------------------------------------  SOURCE CODE : MAIN  ---------------------------------------------------------------------//
    
    public static void main(String args[]){

        //-------------------------------------------------------------------  INTRO  -------------------------------------------------------------------------//
        
        Scanner sc = new Scanner(System.in);
        System.out.println("                                                       ***********************************************************************************");
        System.out.println("                                                                                  >  WELCOME TO IMAGE EDITOR  <                           ");
        System.out.println("                                                       ***********************************************************************************");
        System.out.println();

        //Variable to run the Loop again or Not based on User's Input
        boolean temp = true;

        while (temp) {
        
            System.out.println("                                                       ------------------------->  Enter 1 to run the program  <--------------------------" );
            System.out.println("                                                       ---------------------->  Enter 0 to terminate the program  <-----------------------" );

            //Input Of Run Program Or Not
            String runOrNot = sc.nextLine();

            System.out.println();

            if (runOrNot.equals("1")) {

                System.out.println("====> Provide the file path of the Image : ");
                
                // Path Input and File Creation
                String filePath = sc.nextLine();
                File inputFile = new File(filePath);
                String fileName = inputFile.getName();

                // Checking extension of the image
                int index = fileName.lastIndexOf('.');
                if (index > 0) {
                    String extension = fileName.substring(index+1);

                    if (!extension.equals("jpg") && !extension.equals("jpeg")) {
                        throw new RuntimeException("!!!Please provide '.jpg' or '.jpeg' extension image!!!");
                        }
                }
                else if (index == -1) {
                    throw new RuntimeException("!!!Please provide a valid image format!!!");
                }

                System.out.println();

                System.out.println("====> Select operation to be performed: ");
                System.out.println("(*) Enter 1 for converting image to grayScale.");
                System.out.println("(*) Enter 2 for rotating image clockwise.");
                System.out.println("(*) Enter 3 for rotating image anti - clockwise.");
                System.out.println("(*) Enter 4 for horizontally inverting the image.");
                System.out.println("(*) Enter 5 for vertically inverting the image.");
                System.out.println("(*) Enter 6 for changing the brightness of the image.");
                System.out.println("(*) Enter 7 for blurring the image.");

                //Input Of The Operation
                String input = sc.nextLine();

        //----------------------------------------------------------------  1.GRAYSCALE  --------------------------------------------------------//
            
                if (input.equals("1")) {

                    try {

                        BufferedImage inputImage = ImageIO.read(inputFile);
                        BufferedImage grayScale = convertToGrayScale(inputImage);
                        File grayScaleImage = new File("grayscaleImage.jpeg");
                        ImageIO.write(grayScale , "jpeg" , grayScaleImage);
            
                        } catch (IOException e) {
                            System.out.println();
                            throw new RuntimeException("!!!Please provide the correct path of the image.!!!");
                            }

                    System.out.println();
                    System.out.println("====> Successfully converted the image to grayScale.");
                    System.out.println();

                    }

        //---------------------------------------------------------------  2.CLOCKWISE ROTATE  ----------------------------------------------------//
            
                else if (input.equals("2")) {

                    System.out.println();
                    System.out.println("Enter degree by which image has to be rotated (POSITIVE MULTIPLE OF 90) : ");
                    double degree = sc.nextDouble();
                    
                    //To Consume The Enter In Input
                    sc.nextLine();

                    //Edge Case : 
                    if (degree % 90 != 0) {
                        throw new RuntimeException("!!!Provided input is not a multiple of 90!!!");
                    }
                    //Edge Case : 
                    if (degree < 0) {
                        throw new RuntimeException("!!!Please provide a positive input!!!");
                    }

                    try {
                        
                        BufferedImage inputImage = ImageIO.read(inputFile);
                        BufferedImage rotateClockwise = rotateClockwise(inputImage , (int)degree);
                        File rotateClockwiseImage = new File("rotatedClockwiseImage.jpeg");
                        ImageIO.write(rotateClockwise , "jpeg" , rotateClockwiseImage);

                        } catch (IOException e) {
                            System.out.println();
                            throw new RuntimeException("!!!Please provide the correct path of the image.!!!");
                            }

                    System.out.println();
                    System.out.println("====> Successfully rotated the image clockwise.");
                    System.out.println();

                    }

        //--------------------------------------------------------------  3.ANTI - CLOCKWISE ROTATE  -------------------------------------------------//

                else if (input.equals("3")) {

                    System.out.println();
                    System.out.println("Enter degree by which image has to be rotated (POSITIVE MULTIPLE OF 90) : ");
                    int degree = sc.nextInt();

                    //To Consume The Enter In Input
                    sc.nextLine();

                    if (degree % 90 != 0) {
                        throw new RuntimeException("!!!Provided input is not a multiple of 90!!!");
                    }

                    if (degree < 0) {
                        throw new RuntimeException("!!!Please provide a positive input!!!");
                    }

                    try {
                        
                        BufferedImage inputImage = ImageIO.read(inputFile);
                        BufferedImage rotateAntiClockwise = rotateAntiClockwise(inputImage , degree);
                        File rotateAntiClockwiseImage = new File("rotatedAntiClockwiseImage.jpeg");
                        ImageIO.write(rotateAntiClockwise , "jpeg" , rotateAntiClockwiseImage);

                        } catch (IOException e) {
                            System.out.println();
                            throw new RuntimeException("!!!Please provide the correct path of the image.!!!");
                            }

                    System.out.println();
                    System.out.println("====> Successfully rotated the image anti - clockwise.");
                    System.out.println();

                    }

        //---------------------------------------------------------------  4.HORIZONTAL INVERSION  -------------------------------------------------//
            
                else if (input.equals("4")) {

                    try {

                        BufferedImage inputImage = ImageIO.read(inputFile);
                        BufferedImage horizontalInvert = horizontalInvert(inputImage);
                        File horizontalInvertImage = new File("horizontalInverted.jpeg");
                        ImageIO.write(horizontalInvert , "jpeg" , horizontalInvertImage);

                        } catch (IOException e) {
                            System.out.println();
                            throw new RuntimeException("!!!Please provide the correct path of the image.!!!");
                            }

                    System.out.println();
                    System.out.println("====> Successfully horizontally inverted the image.");
                    System.out.println();

                    }

        //----------------------------------------------------------------  5.VERTICAL INVERSION  ---------------------------------------------------//        
           
                else if (input.equals("5")) {

                    try {

                        BufferedImage inputImage = ImageIO.read(inputFile);
                        BufferedImage verticalInvert = verticalInvert(inputImage);
                        File verticalInvertImage = new File("verticalInverted.jpg");
                        ImageIO.write(verticalInvert , "jpg" , verticalInvertImage);

                        } catch (IOException e) {
                            System.out.println();
                            throw new RuntimeException("!!!Please provide the correct path of the image.!!!");
                            }

                    System.out.println();
                    System.out.println("====> Successfully vertically inverted the image.");
                    System.out.println();

                    }

        //-----------------------------------------------------------------  6.BRIGTHNESS CHANGE  ----------------------------------------------------//        
            
                else if (input.equals("6")) {

                    System.out.println();
                    System.out.println("Enter % change in brightness [-100 to 100]: ");
                    double changeInBrightness = sc.nextDouble();

                    //To Consume The Enter In Input
                    sc.nextLine();

                    //EDGE CASE :
                    if(changeInBrightness > 100 || changeInBrightness < -100){
                        throw new RuntimeException("!!!Please provide value from the mentioned range!!!");
                    }

                    try {

                        BufferedImage inputImage = ImageIO.read(inputFile);
                        BufferedImage changeBrightness = changeBrightness(inputImage , changeInBrightness);
                        File changeBrightnessImage = new File("changedBrightnessImage.jpeg");
                        ImageIO.write(changeBrightness , "jpeg" , changeBrightnessImage);

                        } catch (IOException e) {
                            System.out.println();
                            throw new RuntimeException("!!!Please provide the correct path of the image.!!!");
                            }

                    System.out.println();
                    System.out.println("====> Successfully changed the brightness of the image.");
                    System.out.println();

                    }

        //---------------------------------------------------------------------  7.BLURRNESS  -----------------------------------------------------------//          
            
                else if (input.equals("7")) {

                    System.out.println();
                    System.out.println("Enter blurrness level, any integer from 3 to 40 (3 being least blurry) : ");
                    double blurrnessLevel = sc.nextDouble();
                    sc.nextLine();

                    //EDGE CASE : 
                    if (blurrnessLevel < 3 || blurrnessLevel > 40) {
                        throw new RuntimeException("!!!Provided input is not in the range [3 , 40]!!!");
                    }
                    //EDGE CASE : 
                    if (blurrnessLevel % 1 != 0) {
                        throw new RuntimeException("!!!Please provide an integral value!!!");
                    }
                    try {

                        BufferedImage inputImage = ImageIO.read(inputFile);
                        BufferedImage blurring = blurring(inputImage , (int)blurrnessLevel);
                        File blurringImage = new File("blurredImage.jpeg");
                        ImageIO.write(blurring , "jpeg" , blurringImage);

                        } catch (IOException e) {
                            System.out.println();
                            throw new RuntimeException("!!!Please provide the correct path of the image. !!!");
                            }

                    System.out.println();
                    System.out.println("====> Successfully blurred the image.");
                    System.out.println();

                    }


        //-----------------------------------------------------------  EDGE CASE : USER INVALID INPUT OF OPERATION  -------------------------------------------------------------//    
                
                else {

                    System.out.println();
                    throw new RuntimeException("!!!Please provide a valid operation!!!");

                    }
                }
        //------------------------------------------------------------------------  EXIT THE LOOP -----------------------------------------------------------------------------------//
            
            else if (runOrNot.equals("0")) {

                System.out.println();
                System.out.println("====> Program terminated successfully...");
                
                //To Terminate The Loop
                temp = false;

                }
        //-----------------------------------------------------------  EDGE CASE : USER INVALID INPUT TO RUN LOOP OR NOT  -------------------------------------------------------------//
            else {
                throw new RuntimeException("Please provide a valid input!!!");
                }

        }
    }
}