/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiles_czaja;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/*
*   Tile Class - The model
*/
class Tile
{
    private int shape;
    private int letter;
    private int row;
    private int column;  
    private int red;
    private int blue;
    private int green;
    
    //Default constructor
    public Tile()
    {
        shape = 0;
        letter = 0;
        row = 0;
        column = 0;
        red = 0;
        blue = 0;
        green = 0;
    }
    
    //Non default constructor
    public Tile(int red, int green, int blue, int shape, int letter, int row, int column)
    { 
        setShape(shape);
        setLetter(letter);
        setRow(row);
        setColumn(column);
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }
       
    //Get the red color
    public int getRed()
    {
        return red;
    }
    
    //Get the blue color
    public int getBlue()
    {
        return blue;
    }
    
    //Get the green color
    public int getGreen()
    {
        return green;
    }
    
    //Set the shape
    public int getShape()
    {
        return shape;
    }
    
    //Get the letter
    public int getLetter()
    {
        return letter;
    }
    
    //Get the row
    public int getRow()
    {
        return row;
    }
    
    //Get the column
    public int getColumn()
    {
        return column;
    }    
         
    //Set the red color
    public void setRed(int red)
    {
        if(red > 0)
        {
            this.red = red;
        }
        else
        {
            this.red = 0;
        }
    }
    
    //Set the green color
    public void setGreen(int green)
    {
        if(green > 0)
        {
            this.green = green;
        }
        else
        {
            this.green = 0;
        }
    }
    
    //Set the blue color
    public void setBlue(int blue)
    {
        if(blue > 0)
        {
            this.blue = blue;
        }
        else
        {
            this.blue = 0;
        }
    }
    
    //Set the shape
    public void  setShape(int shape)
    {
        if(shape > 0)
        {
            this.shape = shape;
        }
        else
        {
            this.shape = 0;
        }
    }
    
    //Set the ascii character code
    public void setLetter(int letter)
    {
        if( ( (letter >= 'A') && (letter <= 'Z') ) || ( (letter >= 'a') && (letter <= 'z') ) )
        {
            this.letter = letter;
        }
        else
        {
            this.letter = 0;
        }
    }
    
    //Set the row
    public void setRow(int row)
    {
        if(row >= 0)
        {
            this.row = row;
        }
        else
        {
            this.row = 0;
        }
    }
    
    //Set the column
    public void setColumn(int column)
    {
        if(row >= 0)
        {
            this.column = column;
        }
        else
        {
            this.column = 0;
        }
    }     
   
    public String toString()
   {
            //Return a string with the values assigned to the variables
            return String.format("row %d, column %d, letter %d, shape %d, red %d, green %d, blue %d", getRow(), getColumn(), getLetter(), getShape(), getRed(), getGreen(), getBlue());	  
    }
}

/*
*   TilePanel Class - Part of the view
*/
class TilePanel extends JPanel
{    
    //This is needed in order to paint the panel
    private ArrayList<Tile> tile;   
        
    //Default constructor
    public TilePanel()
    {
        tile = null;
    }
    
    //Non default constructor
    public TilePanel(ArrayList<Tile> tile)
    {
        this.tile = tile;
    }
    
    //Overridden paintComponent
    public void paintComponent(Graphics g)
    {
         //Call the paint component of the super class
        super.paintComponent(g);            
        //Loop through the tile object
        for(Tile t: tile)
        {           
            //Set the color for the shape
            g.setColor(new Color(t.getRed(),t.getGreen(),t.getBlue()));
            //Select the shape
            switch(t.getShape())
            {
                case 0:
                {
                    //Draw a filled oval and set the position 
                    g.fillOval(t.getColumn()*50, t.getRow()*50, 50, 50);                    
                    break;
                }
                default:
                {
                    //Draw a filled rectange and set the position
                    g.fillRect(t.getColumn()*50, t.getRow()*50, 50, 50);
                }
            } 
            //Set the color for the color and set to a contrasting color. This is done
            //by adding 128 and performing a modulus operation to keep the value from 
            //exceeding 255.
            g.setColor(new Color( (t.getRed() + 128 ) % 256, (t.getGreen() + 128 ) % 256, (t.getBlue() + 128 ) % 256));
            //Draw the character, and center it to the current shape.
            g.drawString(""+(char)t.getLetter(), (t.getColumn()*50) + 20, (t.getRow()*50) + 30);
        }  
    }  
}

/*
*   TileFrame Class - The other part of the view
*/
class TileFrame extends JFrame
{
    private TileRandomizer tr;
    private TilePanel panelTile;
    private JPanel panelButton;
    private JButton btn;
    
    //Default constructor
    public TileFrame()
    {
        //Create the randomizer object
        tr = new TileRandomizer();        
        //Set the close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        //Set the bounds and start position of the panel
        setBounds(100,100,515,575);
        //Create the containter
	Container container = getContentPane();
        //Set the layout type - in this case just a border layout
	container.setLayout(new BorderLayout());
        //Creat the panel for the images to be generated
	panelTile = new TilePanel();	
        //Create the panel for the button
	panelButton = new JPanel();		
        //Add the component to the container
        container.add(panelTile, BorderLayout.CENTER);
    	//Create a button	
        btn = new JButton("Randomize");
        //Create an anonymous button event
        btn.addActionListener(  new ActionListener() 
                                {
                                    //Event controller for the button
                                    public void actionPerformed(ActionEvent e)
                                    {                                        
                                        //Change the current tiles to something else
                                        tr.changeTileAttrs();
                                        //Repaint the panel
                                        panelTile.repaint();
                                    }
                                }
        );
	//Set the layout of the button	
        panelButton.setLayout(new BorderLayout());
	//Add the button to the panel
        panelButton.add(btn);
        //Add the panel to the container
        container.add(panelButton, BorderLayout.SOUTH);
    }
    
    //Non default constructor
    public TileFrame(ArrayList<Tile> t)
    {   
        //Create a new randomizer object
        tr = new TileRandomizer(t);
        //Set the close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
        //Set the bounds and start position of the panel
        setBounds(100,100,515,575);
        //Create the containter
	Container container = getContentPane();
        //Set the layout type - in this case just a border layout
	container.setLayout(new BorderLayout());
        //Creat the panel for the images to be generated
        //and pass in the object to the panel
	panelTile = new TilePanel(t);	
        //Create the panel for the button     
	panelButton = new JPanel();		
        //Add the component to the container		
        container.add(panelTile, BorderLayout.CENTER);
    	//Create a button	
        btn = new JButton("Randomize");
        //Create an anonymous button event
         btn.addActionListener(  new ActionListener() 
                                {
                                    //Event controller for the button
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        //Change the current tiles to something else
                                        tr.changeTileAttrs();
                                        //Repaint the panel
                                        panelTile.repaint();
                                    }
                                }
        );		
	//Set the layout of the button	
        panelButton.setLayout(new FlowLayout());
	//Add the button to the panel
        panelButton.add(btn);
        //Add the panel to the container
        container.add(panelButton, BorderLayout.SOUTH);
    }
}

/*
*   TilePrinter Class
*/
class TilePrinter
{
    //Default constructor
    public TilePrinter()
    {
        //Empty constructor
    }
    
    //Print data from an arraylist of tiles
    public void printToConsole(ArrayList<Tile> tile)
    {
        //Loop through the arraylist 
        for(Tile t : tile )
        {
            //Call the toString function and print out the data
            System.out.println(t);
        }
    }
}

/*
*   TileRandomizer Class
*/
class TileRandomizer
{
    private ArrayList<Tile> tile;
    private Random random;
    
    //Constructor
    public TileRandomizer()
    {
        tile = null;
        random = new Random();
    }
    
    //Non default constructor
    public TileRandomizer(ArrayList<Tile> tile)
    {
        this.tile = tile;
        random = new Random();
    }
    
    //Build a new tile with random paramters
    public Tile buildTileNew(int row, int column)
    {
       Tile t;
            
        //Return the newly created tile
        return t = new Tile(random.nextInt(255), 
                random.nextInt(255), 
                random.nextInt(255), 
                random.nextInt(2),
                random.nextInt(26) + 65,
                row,
                column);  
    }
    
    //Change an existing individual tile attribute
    public ArrayList<Tile> changeTileAttr(ArrayList<Tile> tile, int row, int column)
    {   
        //Loop through the arraylist
        for(Tile t : tile)
        {
            //Find the row and column in the arraylist
            if((t.getColumn() == column) && (t.getRow() == row))
            {
                //Randomize the selected tile
                t.setLetter(random.nextInt(26) + 65);
                t.setShape(random.nextInt(2));
                t.setRed(random.nextInt(255));
                t.setGreen(random.nextInt(255));
                t.setBlue(random.nextInt(255));
                break;
            }
        }        
        //Return the arraylist
        return tile;
    }
    
    //Change an existing arraylist of tile attributes
    public ArrayList<Tile> changeTileAttrs()
    {
        //Loop through the array list and assign new values
        for(Tile t : tile)
        {
            //Randomize the values
            t.setLetter(random.nextInt(26) + 65);
            t.setShape(random.nextInt(2));
            t.setRed(random.nextInt(255));
            t.setGreen(random.nextInt(255));
            t.setBlue(random.nextInt(255));            
        }
    
        return tile;
    }
}

/**
 *
 * @author Bryon
 */
public class Tiles_Czaja 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //Instantiates the objects
        ArrayList<Tile> tile = new ArrayList<Tile>();   
        TileRandomizer tr = new TileRandomizer();
        TilePrinter tilePrinter = new TilePrinter();
       
        //Go through the rows
        for(int r = 0; r < 10; r++)
        {
            //Go through the column
            for(int c = 0; c < 10; c++)
            {
                //Create a new tile object at the specified row and column
                //and randomize the other variables
                Tile t = tr.buildTileNew(r, c);
                //Add the new tile object to the arraylist
                tile.add(t);                
            }
        }      
        
        //Print to the console
        tilePrinter.printToConsole(tile);     
        //Create the frame object
        TileFrame tf = new TileFrame(tile);
        //Set it to visible
        tf.setVisible(true);    
    }
}



