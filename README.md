# LimeLight
> An application which promotes co-ordination among rescue workers

## Problem Statement

The site of a natural disaster is chaotic to its core. In a sense it symbolises disorder. There is rush all over the place
and no matter how well-trained the rescue workers are, they will definitely perform redundant actions owing to the fact that
there is no singular entity governing and co-ordinating their actions in real-time. Some degree of co-ordination is still 
achieved via cellular phones, walky takies etc but one cannot broadcast his actions clearly to everyone in his peer network 
due to time constraints leading to unoptimized allocation of human resources on localised rescue operations. Hence, the presence
of a governing entity is of paramount importance.

## Enter LimeLight

**LimeLight** is a disaster management ecosystem which allows rescue operations to be carried out in the best possible manner.
It would comprise of an android application and a central server.

The first step is locating victims. The mobile phones of victims will continuously send POST requests to a central server with
their location as the payload. Their location would be rendered in a Google Map Interface in the rescuer's phone. A cluster
of locations would comprise a rescue zone which would be circular in nature and also rendered on the Map Interface. Now rescue
operations can be carried out on a zonal basis. In case there is no cellular connectivity in the area, the victims would be located
via their phone's **bluetooth**. The range of a bluetooth is approximately 100 metres. Whenever a rescue worker steps within
100 metre radius of a victim, he would be notified of the victim's location.

The second step is carrying out the rescue operations in the most optimal manner. It can done by designating specific roles to
the rescue workers and alloting manpower to each rescue zone. There would be mainly 3 roles:-

* Scout - One who surveys the area and reports the damage and status of victims
* Medic - One who is responsible for providing first-aid treatment to the victims
* Lifter - One who removes wreckage and pulls out victims from underneath

There are only 3 roles in the initial draft but the infrastructure of the application would be such that it would allow defining
any number of custom roles as per the requirements.

Now comes the allocation of manpower. The manpower requirements of each zone would be estimated using machine learning.
For Ex:- A rescue zone containing 10 people might require 2 scouts, 5 lifters and 4 medics.
In case the prediction is a bit off the mark, the rescue worker would have the full accessibility to change the requirements
of a zone then and there.

An user using this application will be able to perform the following tasks:-

* View which zones are assigned to which users in the Map Overlay Mode
* Assign himself to a zone and move out to help those in need
* Dissociate himself from a zone when most probably everybody in the zone has been saved
* Request reinforcements from the pool of users who are currently un-assigned
* Assign a task to a user or group of users ( Would require admin privileges to prevent abuse )
* Communicate with other rescue workers or victims

In this way **LimeLight** would orchestrate rescue operations, suppress redundant actions and serve as a guideline for carrying
out fast and elegant rescue operations.




