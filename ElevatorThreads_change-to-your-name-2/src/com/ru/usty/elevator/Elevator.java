package com.ru.usty.elevator;

public class Elevator implements Runnable 
{
	int peopleInElevator = 0;
	int peopleGoingOut = 0;
	int numberOfFloors = 0;
	
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
				for(int k = 0; k < numberOfFloors; k++)
				{

					ElevatorScene.scene.setCurrentFloorForElevator(k); //segjum a hvada haed lyftan er
					
					
					/*
					try {
						ElevatorScene.elevatorWaitMutex.acquire(); // tokum fra leifid til ad fara i lyftuna
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
							peopleGoingOut = ElevatorScene.scene.getNumberOfPeopleGoingOutAtFloor(k);
					
							for(int i = 0 ; i < peopleGoingOut; i++)
							{
								System.out.println("Folk ma fara ut" + i);

								ElevatorScene.elevatorGoOut.release();
							}
					//ElevatorScene.elevatorWaitMutex.release(); //gefa leifi til ad fara ut ur lyftu
						
															try {
																Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
						/*
						try {
							ElevatorScene.elevatorWaitMutex.acquire(); //taka leifi til ad fara inni lyftu
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
								peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
								
								for(int i = 0; i < (6 - peopleInElevator); i++)
								{
									System.out.println("folk ma fara inn" + i);

									ElevatorScene.semaphore1.release();
								}
								

							//ElevatorScene.elevatorWaitMutex.release();//gefa leifi a lyfuna aftur
									
															try {
																Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														
							
								if(ElevatorScene.elevatorWaitMutex.tryAcquire()){
									//taka leifi til ad fara inni lyftu
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
				for(int k = numberOfFloors; k > 0; k--)
				{

					ElevatorScene.scene.setCurrentFloorForElevator(k); //segjum a hvada haed lyftan er
					
					
					/*
					try {
						ElevatorScene.elevatorWaitMutex.acquire(); // tokum fra leifid til ad fara i lyftuna
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
							peopleGoingOut = ElevatorScene.scene.getNumberOfPeopleGoingOutAtFloor(k-1);
					
							for(int i = 0 ; i < peopleGoingOut; i++)
							{
								System.out.println("Folk ma fara ut" + i);

								ElevatorScene.elevatorGoOut.release();
							}
					//ElevatorScene.elevatorWaitMutex.release(); //gefa leifi til ad fara ut ur lyftu
						
															try {
																Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
						/*
						try {
							ElevatorScene.elevatorWaitMutex.acquire(); //taka leifi til ad fara inni lyftu
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
								peopleInElevator = ElevatorScene.scene.getNumberOfPeopleInElevator(1);
								
								for(int i = 0; i < (6 - peopleInElevator); i++)
								{
									System.out.println("folk ma fara inn" + i);

									ElevatorScene.semaphore1.release();
								}
								

							//ElevatorScene.elevatorWaitMutex.release();//gefa leifi a lyfuna aftur
									
															try {
																Thread.sleep(ElevatorScene.VISUALIZATION_WAIT_TIME);
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														
							
								if(ElevatorScene.elevatorWaitMutex.tryAcquire()){
									//taka leifi til ad fara inni lyftu
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
		}
	
	}
	
}
