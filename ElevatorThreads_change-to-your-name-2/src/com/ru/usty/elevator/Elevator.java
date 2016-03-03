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
			while(numberOfFloors-1 > Nfloor){
				peopleIn(Nfloor);			
				fillElevator();	
				Nfloor++;
				peopleOut(Nfloor);
			}
			while(0 < Nfloor){
				peopleIn(Nfloor);			
				fillElevator();	
				Nfloor--;
				peopleOut(Nfloor);
			}
				
		}
	
	}
	private void fillElevator(){
		if(ElevatorScene.elevatorWaitMutex.tryAcquire()){
			peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
			
			for(int i = 0; i < (6 - peopleInElevator); i++)
			{
				System.out.println("fyllum lyftu" + i);

				try {
					ElevatorScene.semaphore1.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ElevatorScene.elevatorWaitMutex.release();
		}
	}
	private void peopleIn(int floor){
		peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
		
		for(int i = 0; i < (6 - peopleInElevator); i++)
		{
			System.out.println("folk ma fara inn" + i);

			ElevatorScene.semaphore1.release();
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

					ElevatorScene.elevatorGoOut.release();
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
