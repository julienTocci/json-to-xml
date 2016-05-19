#!/usr/bin/python
# -*- coding: latin-1 -*-
from pprint import pprint
import json
import os, os.path
import sys
from collections import defaultdict
import logging


#Globals
logging.basicConfig()
LOG = logging.getLogger("Action")
LOG.setLevel(logging.INFO)

###### RUN THIS PROGRAM WITH PYTHON INTERPRETER#########
######SPECIFY THE DIRECTORY WHERE THE LOG ARE#########
######THE PROGRAM WILL MAKE STAT FOR ALL LOG WITH .JSON IN THE DIRECTORY########
PATH = 'C:/Users/julien/Desktop/LOG'
################# Action Object #####################
class Action(object):
    name =""
    cost_list = []
    amount_list = []
    hasAmount = False
    numberof = 0


    # The class "constructor"
    def __init__(self, name, hasAmount):
        self.name = name
        self.hasAmount = hasAmount

    def getArrayCost(self):
        return self.cost_list
    def getArrayAmount(self):
        return self.amount_list
    def emptyArrays(self):
        self.cost_list = []
        self.amount_list = []

    def emptyNumberof(self):
        self.numberof = 0
####################################################

################# RESULT Object ####################
class Result(object):

    def __init__(self):
        self.average_fish = 0
        self.average_flower = 0
        self.average_fruit = 0
        self.average_fur = 0
        self.average_ore = 0
        self.average_quartz = 0
        self.average_sugar = 0
        self.average_wood = 0
        self.average_move_to = 0
        self.average_stop = 0
        self.average_fly = 0
        self.average_scan = 0
        self.average_heading = 0
        self.average_scout = 0

        self.average_fish_amount = 0
        self.average_flower_amount = 0
        self.average_fruit_amount = 0
        self.average_fur_amount = 0
        self.average_ore_amount = 0
        self.average_quartz_amount = 0
        self.average_sugar_amount = 0
        self.average_wood_amount = 0

    def choose_value(self,ch,flag_cost,number):
        if(flag_cost == "amount"):
            if(ch == "FUR"):
                if(self.average_fur_amount == 0):
                    self.average_fur_amount = number
                else:
                    self.average_fur_amount = (self.average_fur_amount+number)/2
            elif(ch == "FLOWER"):
                if(self.average_flower_amount == 0):
                    self.average_flower_amount = number
                else:
                    self.average_flower_amount = (self.average_flower_amount+number)/2
            elif(ch == "FRUITS"):
                if(self.average_fruit_amount == 0):
                    self.average_fruit_amount = number
                else:
                    self.average_fruit_amount = (self.average_fruit_amount+number)/2
            elif(ch == "ORE"):
                if(self.average_ore_amount == 0):
                    self.average_ore_amount = number
                else:
                    self.average_ore_amount = (self.average_ore_amount+number)/2
            elif(ch == "QUARTZ"):
                if(self.average_quartz_amount == 0):
                    self.average_quartz_amount = number
                else:
                    self.average_quartz_amount = (self.average_quartz_amount+number)/2
            elif(ch == "SUGAR"):
                if(self.average_sugar_amount == 0):
                    self.average_sugar_amount = number
                else:
                    self.average_sugar_amount = (self.average_sugar_amount+number)/2
            elif(ch == "WOOD"):
                if(self.average_wood_amount == 0):
                    self.average_wood_amount = number
                else:
                    self.average_wood_amount = (self.average_wood_amount+number)/2
            elif(ch == "FISH"):
                if(self.average_fish_amount == 0):
                    self.average_fish_amount = number
                else:
                    self.average_fish_amount = (self.average_fish_amount+number)/2

        else :
            if(ch == "FUR"):
                if(self.average_fur == 0):
                    self.average_fur = number
                else:
                    self.average_fur = (self.average_fur+number)/2
            elif(ch == "FLOWER"):
                if(self.average_flower == 0):
                    self.average_flower = number
                else:
                    self.average_flower = (self.average_flower+number)/2
            elif(ch == "FISH"):
                if(self.average_fish == 0):
                    self.average_fish = number
                else:
                    self.average_fish = (self.average_fish+number)/2
            elif(ch == "FRUITS"):
                if(self.average_fruit == 0):
                    self.average_fruit = number
                else:
                    self.average_fruit = (self.average_fruit+number)/2
            elif(ch == "ORE"):
                if(self.average_ore == 0):
                    self.average_ore = number
                else:
                    self.average_ore_ = (self.average_ore+number)/2
            elif(ch == "QUARTZ"):
                if(self.average_quartz == 0):
                    self.average_quartz = number
                else:
                    self.average_quartz = (self.average_quartz_+number)/2
            elif(ch == "SUGAR"):
                if(self.average_sugar == 0):
                    self.average_sugar = number
                else:
                    self.average_sugar = (self.average_sugar+number)/2
            elif(ch == "WOOD"):
                if(self.average_wood == 0):
                    self.average_wood = number
                else:
                    self.average_wood = (self.average_wood+number)/2


            elif(ch == "move_to"):
                if(self.average_move_to == 0):
                    self.average_move_to = number
                else:
                    self.average_move_to = (self.average_move_to+number)/2
            elif(ch == "stop"):
                if(self.average_stop == 0):
                    self.average_stop = number
                else:
                    self.average_stop = (self.average_stop+number)/2
            elif(ch == "fly"):
                if(self.average_fly == 0):
                    self.average_fly = number
                else:
                    self.average_fly = (self.average_fly+number)/2
            elif(ch == "scan"):
                if(self.average_scan == 0):
                    self.average_scan = number
                else:
                    self.average_scan = (self.average_scan+number)/2
            elif(ch == "heading"):
                if(self.average_heading == 0):
                    self.average_heading = number
                else:
                    self.average_heading = (self.average_heading+number)/2
            elif(ch == "scout"):
                if(self.average_scout == 0):
                    self.average_scout = number
                else:
                    self.average_scout = (self.average_scout+number)/2
            else:
                print("Key Error")



####################################################


################# MakeMetrics Object ###############
class MakeMetrics(object):

    def __init__(self):

        self.fish_r = None
        self.flower_r = None
        self.fruit_r = None
        self.fur_r = None
        self.ore_r = None
        self.quartz_r = None
        self.sugar_r = None
        self.wood_r = None
        self.move_to = None
        self.stop = None
        self.fly = None
        self.scan = None
        self.heading = None
        self.scout = None

    def _make_action_(self,name,hasAmount):
        return Action(name,hasAmount)


    # CREATING RESOURCE
    def _set_action_arrays(self):
        self.fish_r = self._make_action_("FISH",True)
        self.flower_r = self._make_action_("FLOWER",True)
        self.fruits_r = self._make_action_("FRUITS",True)
        self.fur_r = self._make_action_("FUR",True)
        self.ore_r = self._make_action_("ORE",True)
        self.quartz_r = self._make_action_("QUARTZ",True)
        self.sugar_r = self._make_action_("SUGAR_CANE",True)
        self.wood_r = self._make_action_("WOOD",True)
        self.move_to = self._make_action_("move_to",False)
        self.stop = self._make_action_("stop",False)
        self.fly = self._make_action_("fly",False)
        self.scan = self._make_action_("scan",False)
        self.heading = self._make_action_("heading",False)
        self.scout = self._make_action_("scout",False)

        return (self.fish_r, self.flower_r, self.fruits_r, self.fur_r, self.ore_r, self.quartz_r, self.sugar_r, self.wood_r
                , self.move_to, self.stop, self.fly, self.scan, self.heading, self.scout)

    def _get_resource_array(self):
        return self._set_action_arrays();




    def choose_array(self,ch):
        if(ch == "FUR"):
            return self.fur_r
        elif(ch == "FLOWER"):
            return self.flower_r
        elif(ch == "FRUITS"):
            return self.fruits_r
        elif(ch == "ORE"):
            return self.ore_r
        elif(ch == "QUARTZ"):
            return self.quartz_r
        elif(ch == "SUGAR"):
            return self.sugar_r
        elif(ch == "WOOD"):
            return self.wood_r
        elif(ch == "move_to"):
            return self.move_to
        elif(ch == "stop"):
            return self.stop
        elif(ch == "fly"):
            return self.fly
        elif(ch == "scan"):
            return self.scan
        elif(ch == "heading"):
            return self.heading
        elif(ch == "scout"):
            return self.scout
        else:
            print("Key Error")
        '''try:
            {'FISH': lambda : fish(),
            'FLOWER': lambda : flower(),
            'FRUITS': lambda : fruits(),
            'FUR': lambda ch : fur(),
            'ORE': lambda : ore(),
            'QUARTZ': lambda : quartz(),
            'SUGAR': lambda : sugar(),
            'WOOD': lambda : wood(),
            }[ch]()
        except KeyError:
            print("Key Error")
            '''

    @staticmethod
    def calcul_total(array):
        total = 0
        for elem in array :
            total += elem
        return total


    @staticmethod
    def calcul_per_exploit(amounttotal, array):
        return float(amounttotal)/len(array)

####################################################

###############JsonObject###########################

class JSONObject(object):

    def __init__(self):
        """
            File (file): contain the json data

        """
        self.json_data = []
        self.File = None

    def __load_file__(self, File, counter):
        self.File = File
        try:

            print("\n*********************JSON PARSING************* ")
            with open('%s/%s' % (PATH,self.File)) as json_file:
                print("\n********************TREATMENT*****************\n")
                print("Parsing %d th file named : %s" % (counter,self.File))
                self.json_data =json.load(json_file)


        except IOError as e:
            print "I/O error({0}): {1}".format(e.errno, e.strerror)
        except ValueError:
            print "Could not convert data to an integer."
        except:
            print "Unexpected error:", sys.exc_info()[0]

#######################################################

if __name__ == '__main__':

    '''change the dir to locate your repertory which contains the log files '''
    DIR = 'C:/Users/julien/Desktop/LOG/'
    number_of_log_file = len([name for name in os.listdir(DIR) if os.path.isfile(os.path.join(DIR, name))])
    Files = []

    ''' temporary variable to stock the result's amount and cost '''
    amount = 0
    cost = 0

    ''' this counter permit to increment the file reader '''
    counter =0

    ''' Filling the Files Array with all log files '''
    for file in os.listdir(DIR):
        if file.endswith(".json"):
            Files.append(file)

    ''' this variable permit to permut between read the action and its result '''
    is_action = True

    ''' instantiating the necessary object '''
    make_metric = MakeMetrics()
    json_object = JSONObject()
    final_result = Result()


    print("Parsing %d json files || in the DIR %s" % (number_of_log_file,DIR))


    '''Setting the metric object'''
    action_array = make_metric._get_resource_array()

    ''' for each file we are doing some stats '''
    while(counter < number_of_log_file):

        ''' reinitializing action's variable '''
        for r in action_array:
            r.emptyArrays()
            r.emptyNumberof()

        ''' loading the file '''
        json_object.__load_file__(Files[counter], counter)


        ''' extracting the contract (first json object) '''
        contract_json_item = json_object.json_data[0]
        for contract in contract_json_item["data"]["contracts"]:
            print("|| Contract to do : %s %d ||" % (contract["resource"],contract["amount"]))
        print("|| Budget : %d ,  Men : %d ||" % (contract_json_item["data"]["budget"],contract_json_item["data"]["men"]))



        nextarray =""
        ''' z is the total number of action done '''
        z = 0

        ''' contain a string wich permit to differentiate the type of action done '''
        action = ""

        ''' permit to remove the contract from the json data '''
        first = True

        for item in json_object.json_data:
            if(first == True) :
                first = False
                continue
            if('data' not in item):
                print("LALLALA")
                continue

            ''' case we read an action done by the bot explorer '''
            if(is_action):
                if(item["data"]["action"] == "exploit"):
                    nextarray=item["data"]["parameters"]["resource"]
                    action = "exploit"
                    z+=1
                elif(item["data"]["action"] == "move_to"):
                    action = "move_to"
                    nextarray=action
                    z+=1
                elif(item["data"]["action"] == "stop"):
                    action = "stop"
                    nextarray=action
                    z+=1
                elif(item["data"]["action"] == "fly"):
                    action = "fly"
                    nextarray=action
                    z+=1
                elif(item["data"]["action"] == "scan"):
                    action = "scan"
                    nextarray=action
                    z+=1
                elif(item["data"]["action"] == "heading"):
                    action = "heading"
                    nextarray=action
                    z+=1
                elif(item["data"]["action"] == "scout"):
                    action = "scout"
                    nextarray=action
                    z+=1
                else :
                    nextarray= "null"
                    action = "null"
                is_action = False

                ''' case we read the result from the engine '''

            else:

                if(nextarray != "null"):

                    ''' we choose the good array in relation with the previous action '''
                    actualresource = make_metric.choose_array(nextarray)

                    ''' we increase the counter of this specific action '''
                    actualresource.numberof +=1

                    ''' every action has a cost in the result'''
                    actualresource.getArrayCost().append(item["data"]["cost"])

                    ''' but only exploit has an amount in the result '''
                    if(action ==  "exploit"):
                        actualresource.getArrayAmount().append(item["data"]["extras"]["amount"])
                is_action = True


        ''' Printing for each action encountered in the log file, its stats '''
        for r in action_array:
            if(len(r.getArrayCost()) > 0):
                cost = make_metric.calcul_total(r.getArrayCost())
                ''' this part is reserved for resources from the EXPLOIT action '''
                if(r.hasAmount == True):
                    amount = make_metric.calcul_total(r.getArrayAmount())
                    resource_per_exploit = make_metric.calcul_per_exploit(amount, r.getArrayAmount())
                    pa_per_exploit = make_metric.calcul_per_exploit(cost, r.getArrayAmount())
                    final_result.choose_value(r.name,"amount",resource_per_exploit)
                    final_result.choose_value(r.name,"cost",pa_per_exploit)

                    print("\n|| %s ||" %  r.name.upper() )
                    print("|| Ressource : %s recolted a total of %d for a Cost of : %d ||" %(r.name,amount,cost))
                    print("|| RPE(resource per exploit) : %d  for a total of %d exploit ||" % (resource_per_exploit,r.numberof))
                    print("|| PAPE(PA per exploit) : %d  for a total of %d exploit ||" % (round(pa_per_exploit),r.numberof))


                    ''' this part is for all other actions '''
                else:
                    final_result.choose_value(r.name,"cost",round(float(cost)/r.numberof))
                    print("\n|| %s ||" %  r.name.upper() )
                    print("|| Number of  %s :  %d for a Cost of : %d ||" %(r.name,r.numberof,cost))
                    print("|| PAPE(PA per %s) : %d  for a total of %d || \n" % (r.name,round(float(cost)/r.numberof),r.numberof))


        ''' incrementing file reader '''
        counter += 1

    print("\n**************END OF THE LOG READING ***************")

    print("[[ FINAL RESULT ]]")
    print("|| RESOURCE ||")
    print("|| Resource FUR : %d average per exploit  and %d PA per exploit" % (final_result.average_fur_amount, final_result.average_fur))
    print("|| Resource FISH : %d average per exploit  and %d PA per exploit" % (final_result.average_fish_amount, final_result.average_fish))
    print("|| Resource FLOWER : %d average per exploit  and %d PA per exploit" % (final_result.average_flower_amount, final_result.average_flower))
    print("|| Resource ORE : %d average per exploit  and %d PA per exploit" % (final_result.average_ore_amount, final_result.average_ore))
    print("|| Resource QUARTZ : %d average per exploit  and %d PA per exploit" % (final_result.average_quartz_amount, final_result.average_quartz))
    print("|| Resource SUGAR : %d average per exploit  and %d PA per exploit" % (final_result.average_sugar_amount, final_result.average_sugar))
    print("|| Resource WOOD : %d average per exploit  and %d PA per exploit" % (final_result.average_wood_amount, final_result.average_wood))
    print("|| Resource FRUITS : %d average per exploit  and %d PA per exploit" % (final_result.average_fruit_amount, final_result.average_fruit))

    print("|| ACTIONS ||")
    print("|| Action move_to : %d Pa in average per this action" % final_result.average_move_to)
    print("|| Action stop : %d Pa in average per this action" % final_result.average_stop)
    print("|| Action fly : %d Pa in average per this action" % final_result.average_fly)
    print("|| Action scan : %d Pa in average per this action" % final_result.average_scan)
    print("|| Action heading : %d Pa in average per this action" % final_result.average_heading)
    print("|| Action scout : %d Pa in average per this action" % final_result.average_scout)

