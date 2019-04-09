package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller
{
	@FXML public Button Browse;
	@FXML public ComboBox<String> ListFiles;
	@FXML public Button Resolution;
	@FXML public Label SolutionCount;
	@FXML public Label StepCount;
	@FXML public Label ResolutionDuration;
	@FXML public Label Pathlength;
	@FXML public Label ElementsExplored;
	@FXML public GridPane Maze;
	@FXML public ChoiceBox<String> ListAlgo;

	List<String> listMaze = new ArrayList<String>();
	public String indexFile;
	AlgorithmSolver solver;
	Maze currentMaze;
	public void initialize()
	{
		solver = new AlgorithmSolver();
		fillComboBox();
		ListAlgo.getItems().add("Parcour en largeur");
		ListAlgo.setValue("Parcour en largeur");
	}

	public void fillComboBox()
	{
		String path = System.getProperty("user.home") + "\\PATHFINDER\\fileList.txt";
		File file = new File(path);

		if(file.exists()) {
			BufferedReader br = null;
			FileReader fr = null;
			try {
				fr = new FileReader(path);
				br = new BufferedReader(fr);

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					ListFiles.getItems().add(sCurrentLine);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void Resolve()
	{
		if(currentMaze != null) {
            Element<Position> finishAlgo=solver.widthFirst(currentMaze);;

            currentMaze.map =currentMaze.sendResolution(finishAlgo);
            drawMap();
		}
	}

    public void actionBrowseButton(ActionEvent ae) {
		   // création de la boite de dialogue
	    JFileChooser dialogue = new JFileChooser("D:\\Users\\Quentin\\IdeaProjects\\Pathfinder2.0\\src\\maps");
	    dialogue.setCurrentDirectory(new File("D:\\Users\\Quentin\\IdeaProjects\\Pathfinder2.0\\src\\maps"));
	    dialogue.setFileSelectionMode(JFileChooser.FILES_ONLY);

	    FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichiers texte", "txt");
        dialogue.setFileFilter(ff);

	    // affichage
	    dialogue.showOpenDialog(null);
	     
	    // récupération du fichier sélectionné
	    System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
	    if(dialogue.getSelectedFile() != null) {
			indexFile = dialogue.getSelectedFile().getPath();

			if (!ListFiles.getItems().contains(indexFile))
				ListFiles.getItems().add(indexFile);
			ListFiles.getSelectionModel().select(ListFiles.getItems().indexOf(indexFile));
			String path = System.getProperty("user.home") + "\\PATHFINDER";
			File file = new File(path);
			System.out.println(path);
			LinkedList<String> allText = new LinkedList<>();
			Writer writer = null;
			try {
				if (!new File(path + "\\fileList.txt").exists()) {
					file.mkdirs();
					path += "\\fileList.txt";
					file = new File(path);
					file.createNewFile();
				} else {
					BufferedReader br = null;
					FileReader fr = null;
					path += "\\fileList.txt";
					try {
						fr = new FileReader(path);
						br = new BufferedReader(fr);

						String sCurrentLine;

						while ((sCurrentLine = br.readLine()) != null) {
							allText.add(sCurrentLine);
						}
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (!allText.contains(indexFile)) {
					allText.add(indexFile);
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(path), "utf-8"));
						for (String s : allText
						) {
							writer.write(s + System.lineSeparator());
						}

						writer.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void setCurrentMaze(ActionEvent e)
	{
		currentMaze = new Maze();
		currentMaze.readFile(ListFiles.getValue());
		Maze.getColumnConstraints().clear();
		Maze.getRowConstraints().clear();
		Maze.setGridLinesVisible(true);
		final int numCols = currentMaze.sizeY ;
		final int numRows = currentMaze.sizeX - 4 ;
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPrefWidth(32);
			colConst.setMinWidth(32);
			Maze.getColumnConstraints().add(colConst);
		}
		for (int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPrefHeight(32);
			rowConst.setMinHeight(32);
			Maze.getRowConstraints().add(rowConst);
		}
		drawMap();
	}

	public void drawMap()
	{
		for(int i = 0; i < currentMaze.sizeY ; i ++)
		{
			for(int j = 0; j < currentMaze.sizeX ; j++)
			{
				ImageView v;
				if(currentMaze.map[i][j] == '0')
					v = new ImageView("ressources/grass.jpg");
				else if(currentMaze.map[i][j] == '1')
				{
					v = new ImageView("ressources/dirt.jpg");
				}
				else
                {
                    v = new ImageView("ressources/grass2.jpg");

                }
				Maze.add(v,i,j);
			}
		}
	}

	 public void  indexFiles() {
		while (indexFile != null){

			listMaze.add(indexFile);

		}
	}

	private void SelectAlgorithm(){
		
	}
}
