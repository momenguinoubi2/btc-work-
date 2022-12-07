package tn.esprit.spring.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import tn.esprit.spring.entities.Invitation;
@NoArgsConstructor
public class xlsParser {
	
ReadCell rc = new ReadCell();

 List<Invitation> invitations = new ArrayList<Invitation>();

public static int nbrows = ReadCell.getNumRows();

public  List<Invitation> parser (){
for (int i =1 ; i<nbrows ; i++) {
	Invitation invitation =  new Invitation();
	
	invitation.setEmail(rc.ReadCellData(i, 0));
	invitation.setFullName(rc.ReadCellData(i, 1));
	invitation.setPhone(rc.ReadIntCellData(i, 2));
	
	invitations.add(invitation);
}

	return invitations;

}

}