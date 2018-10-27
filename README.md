# LimeLight
> Disaster relief ecosystem

> Installation APK available [here](https://github.com/CurlSudoBash/LimeLight/tree/master/bin)

## Problem Statement

Present-day disaster relief has two major issues. These are:

1. Locating potential victims quickly to maximize their survival chances
2. Coordinating relief and rescue operations through automated mechanisms

For example, consider the scenario of an earthquake. The best present-day relief efforts in the event of an earthquake should consist of surveying affected sites to estimate the damage, followed by initiating lifting operations in the wreckage to locate victims. Medics should be available to provide first-aid to the rescued people. This suffers from some major problems. Firstly, rescuers never have a good idea about the number of buried people in different locations. This information, if present, could greatly help in prioritizing and scheduling relief efforts in different locations. Secondly, the site of a natural disaster is chaotic to its core. In a sense it symbolises disorder. There is rush all over the place and no matter how well-trained the rescue workers are, they will definitely perform redundant actions owing to the fact that there is no singular entity governing and co-ordinating their actions in real-time. Some degree of co-ordination is still achieved via cellular phones, walky talkies, etc. but one cannot broadcast his/her actions clearly to everyone in his/her peer network due to time constraints. This leads to unoptimized allocation of human resources on localised rescue operations. There might be sites in urgent need that are starved of rescue efforts due to this gap in communication. Hence, the presence of a governing entity that bridges this gap is of paramount importance.

## Enter LimeLight

**LimeLight** is a disaster management ecosystem which allows rescue operations to be carried out in the best possible manner. It would comprise of an android application and a central server [LimeLightCentrum](https://github.com/CurlSudoBash/LimeLightCentrum).

The first step is locating victims. The mobile phones of victims will continuously send POST requests to a central server with their location as the payload. This location polling can be implemented in two ways: it could either be a persistent sending of location data at regular intervals, or it could be triggered by a governing authority at a specific location in the event of a disaster.

These locations would be rendered in a Google Map Interface in the rescuers' phones. A cluster of locations would comprise a rescue zone. By clicking on a marker in the map interface, information such as the number of devices in the cluster shall be visible. Now rescue operations can be carried out on a zonal basis. In case, there is no internet connectivity at a disaster site, the victims would be located via **Wifi Direct Channels** available in their phones. The range of a wifi direct channel is approximately 60 metres. Whenever a rescue worker steps within 60 metre radius of a victim, he would be notified of the victim's presence. Also the Wifi Direct Channels would play a crucial role in registering the location of a user on the server. If the user doesn't have an active internet connection, he would forward his location to someone within 60m and if that person has an internet connection he would register the sender's location else this process would continue again. As such the Wifi Direct Relay System ensures that everyone is located on the maps.

The second step is carrying out the rescue operations in the most optimal manner. There are mainly two kinds of people involved in the relief effort:

* Rescuer - One who transfers victims from the disaster site
* Medic - One who is responsible for providing first-aid treatment to the victims

Now comes the allocation of manpower. The manpower requirements of each zone would be estimated using **machine learning**. For Ex:- A rescue zone containing 10 people might require 5 rescuers and 3 medics. In case the prediction is a bit off the mark, the rescue worker would have the full accessibility to change the requirements of a zone then and there.

An individual using this application will be able to perform the following tasks:-

* View the number of rescuers and medics present in a zone
* Assign himself to a zone and move out to help those in need
* Dissociate himself from a zone when most probably everybody in the zone has been saved
* Request reinforcements from the pool of users who are currently un-assigned
* Assign a task to a user or group of users ( Would require admin privileges )
* Communicate with other rescue workers or victims

In this way **LimeLight** would provide an ecosystem to orchestrate rescue operations in a fast and elegant manner.




