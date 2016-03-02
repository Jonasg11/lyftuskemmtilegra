package com.ru.usty.elevator;

public class Elevator implements Runnable {
	int peopleInElevator = 0;
	int peopleGoingOut = 0;
	int numberOfFloors = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(ElevatorScene.elevatorsMayDie){
				return;
			}
			numberOfFloors = ElevatorScene.scene.getNumberOfFloors();
			for(int k = 0; k < numberOfFloors; k++)
			{
				System.out.println(k);
				ElevatorScene.scene.setCurrentFloorForElevator(k);
				
				
				
				try {
					ElevatorScene.personInElevatorCountMutex.acquire();
					
					peopleGoingOut = ElevatorScene.scene.getNumberOfPeopleGoingOutAtFloor(k); //byrjum á því að athuga hvort að einhver þurfi að fara út og hleypum þeim þá út
					System.out.println(peopleGoingOut);
					for(int i = 0; i < peopleGoingOut; i++)
					{
						System.out.println("folk fer ut " + i);								
						ElevatorScene.elevatorGoOut.release(); //herna er ég að úthluta leyfum til að fólk meigi fara út
					}
					ElevatorScene.personInElevatorCountMutex.release();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//herna ættu persons að vera að fara út úr lyftunni
//------------------------------------------------------------------------------------------------------------------------
				
				try {
					
					ElevatorScene.personInElevatorCountMutex.acquire();	//her náum við í fjöldan af fólki sem að er í lyftuni og við þurfum að passa að enginn annar sé að breytta því á meðan
					peopleGoingOut = ElevatorScene.scene.getNumberOfPeopleGoingOutAtFloor(k); //herna er ég að passa að ef að einhver náði ekki að fara út að sá sem að komi næst inn komi ekki inn og fari beint út
					if(peopleGoingOut != 0)
					{
						for(int i = 0; i < peopleGoingOut; i++)
						{
							System.out.println("ekki allir komsut inn " + i);	
							ElevatorScene.elevatorGoOut.acquire();
						}
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1); 
				for(int i = 0; i < (6 - peopleInElevator); i++)
				{
					System.out.println("folk ma fara inn " + i);
					ElevatorScene.semaphore1.release(); //signal
				}
					ElevatorScene.personInElevatorCountMutex.release();
			
			try {
				Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//herna ættu persons að fara inní lyftuna
//------------------------------------------------------------------------------------------------------------------------
			
			
			
			//herna tjekkum við á því hvort að lyftan sé full, ef að við náum ekki að aquire þá vitum við að hun er full
			//annars fyllum við hana
			if(k+1 != numberOfFloors){
				ElevatorScene.scene.setCurrentFloorForElevator(k+1);
			}
			else ElevatorScene.scene.setCurrentFloorForElevator(k-1);
			
			if(ElevatorScene.elevatorWaitMutex.tryAcquire())
			{
				try {
					ElevatorScene.personInElevatorCountMutex.acquire();
					peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
					ElevatorScene.personInElevatorCountMutex.release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for(int i = 0; i < (6 - peopleInElevator); i++)
				{
					try {
						ElevatorScene.semaphore1.acquire();
						System.out.println("fyllum uppi lyftu " + i);	
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //signal
				
			}
			}
			ElevatorScene.elevatorWaitMutex.release();
			
			
			}
			
//--------------------------------------------------------------------------------------------------------------------------
			//fer niður aftur 
			
			for(int k = numberOfFloors-1; k > 0; k-- ){
				ElevatorScene.scene.setCurrentFloorForElevator(k);
				try {
					ElevatorScene.personInElevatorCountMutex.acquire();
					peopleGoingOut = ElevatorScene.scene.getNumberOfPeopleGoingOutAtFloor(k); //byrjum á því að athuga hvort að einhver þurfi að fara út og hleypum þeim þá út
					for(int i = 0; i < peopleGoingOut; i++)
					{
																
						ElevatorScene.elevatorGoOut.release(); //herna er ég að úthluta leyfum til að fólk meigi fara út
					}
					ElevatorScene.personInElevatorCountMutex.release();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//herna ættu persons að vera að fara út úr lyftunni
//------------------------------------------------------------------------------------------------------------------------
				
				try {
					
					ElevatorScene.personInElevatorCountMutex.acquire();	//her náum við í fjöldan af fólki sem að er í lyftuni og við þurfum að passa að enginn annar sé að breytta því á meðan
					peopleGoingOut = ElevatorScene.scene.getNumberOfPeopleGoingOutAtFloor(k); //herna er ég að passa að ef að einhver náði ekki að fara út að sá sem að komi næst inn komi ekki inn og fari beint út
					if(peopleGoingOut != 0)
					{
						for(int i = 0; i < peopleGoingOut; i++)
						{
							ElevatorScene.elevatorGoOut.acquire();
						}
					}
					peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1); 
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i = 0; i < (6 - peopleInElevator); i++)
				{
					ElevatorScene.semaphore1.release(); //signal
				}
					ElevatorScene.personInElevatorCountMutex.release();
			
			try {
				Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//herna ættu persons að fara inní lyftuna
//------------------------------------------------------------------------------------------------------------------------
			
			
			
			//herna tjekkum við á því hvort að lyftan sé full, ef að við náum ekki að aquire þá vitum við að hun er full
			//annars fyllum við hana
			if(ElevatorScene.elevatorWaitMutex.tryAcquire())
			{
				try {
					ElevatorScene.personInElevatorCountMutex.acquire();
					peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
					ElevatorScene.personInElevatorCountMutex.release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				for(int i = 0; i < (6 - peopleInElevator); i++){
					try {
						ElevatorScene.semaphore1.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //signal
				
			}
			}
			ElevatorScene.elevatorWaitMutex.release();
			
			
			}
	}
	
	}
	
}
