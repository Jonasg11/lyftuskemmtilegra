package com.ru.usty.elevator;

public class Elevator implements Runnable 
{
	int peopleInElevator = 0;
	int peopleGoingOut = 0;
	int numberOfFloors = 0;
	int Nfloor = 0;
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			if(ElevatorScene.elevatorsMayDie)
			{
				return;
			}
			System.out.println("Lyfta a lifi");
			numberOfFloors = ElevatorScene.scene.getNumberOfFloors();
			//her erum vid med tvaer while lykkjur, fyrsta til ad fara upp og svo naesta til ad fara nidur
			while(numberOfFloors-1 > Nfloor){
				peopleIn(Nfloor);			
				fillElevator(Nfloor);	
				Nfloor++;
				peopleOut(Nfloor);
			}
			while(0 < Nfloor){
				peopleIn(Nfloor);			
				fillElevator(Nfloor);	
				Nfloor--;
				peopleOut(Nfloor);
			}
				
		}
	
	}
	private void fillElevator(int floor){
		if(ElevatorScene.scene.tryToGetSema(floor)){ //her reynum vid ad na mutexnum sem ad vid notum i person lika, ef ad tad tekst ekki
													//vitum vid ad lyftan er full og ta turfum vid ekki ad "fylla" hana	
			peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
			
			for(int i = 0; i < (6 - peopleInElevator); i++)
			{
				System.out.println("fyllum lyftu" + i);
					ElevatorScene.scene.getSemaphore(floor);//tokum semaphorurnar sem ad gefa leyfi fyrir ad fara inni lyftu
				
			}
			ElevatorScene.scene.setWaitSemaphore(floor);
		}
		
	}
	private void peopleIn(int floor){
			//herna tjekkum vid hvad tad eru margir i lyftunni
			ElevatorScene.scene.setCurrentFloorForElevator(floor);
			peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
		
		
		
		for(int i = 0; i < (6 - peopleInElevator); i++)
		{
			System.out.println("folk ma fara inn" + i);
			ElevatorScene.scene.setSemaphore(floor); //gefum leyfi fyrir ad fara inni lyftuna
			//ElevatorScene.semaphore1.release();
		}
			
		Sleep();
	}
	private void peopleOut(int floor){
		System.out.println("hæð " + floor);
		ElevatorScene.scene.setCurrentFloorForElevator(floor); //segjum a hvada haed lyftan er
		
				peopleGoingOut = ElevatorScene.scene.getNumberOfPeopleGoingOutAtFloor(floor);
		
				for(int i = 0 ; i < peopleGoingOut; i++)
				{
					System.out.println("Folk ma fara ut" + i);

					ElevatorScene.scene.setOutSemaphore(floor); // her gefum vid leyfi til ad fara ut ur lyftunni
				}
			Sleep();
												
	}
	private void Sleep(){
		try {
			Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
