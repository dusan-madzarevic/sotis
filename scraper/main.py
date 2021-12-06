import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import pandas as pd

from classes import Programme, Course

if __name__ == '__main__':
    driver = webdriver.Chrome('./chromedriver')
    driver.get("http://www.ftn.uns.ac.rs/1802705466/studijski-programi--akreditacija-2020-")
    oa = driver.find_elements(By.XPATH, '//div[@id="affix-osnovne"]/div[@class="panel-body"]/a')
    os = driver.find_elements(By.XPATH, '//div[@id="affix-specijalisticke"]/div[@class="panel-body"]/a')
    ma = driver.find_elements(By.XPATH, '//div[@id="affix-master"]/div[@class="panel-body"]/a')
    ms = driver.find_elements(By.XPATH, '//div[@id="affix-master-strukovne"]/div[@class="panel-body"]/a')
    doc = driver.find_elements(By.XPATH, '//div[@id="affix-doktorske"]/div[@class="panel-body"]/a')

    programmes = {}
    courses = {}

    programmeOutput = []
    courseOutput = []
    subjectOutput = []
    i = 0
    for programme in oa:
        if i < 1:
            curProg = Programme()
            curProg.name = programme.text.replace(" ","_")
            curProg.id = programme.get_attribute("href").split("/")[3]

            programmes[curProg.id] = curProg

            driver.execute_script("window.open('"+programme.get_attribute("href")+"')")
            driver.switch_to.window(driver.window_handles[-1])
            courseList = driver.find_elements(By.XPATH, '//a[starts-with(@id,\'viewPredmet\')]')
            duration = driver.find_element(By.XPATH, '//h4[.//small[text() = \'Trajanje (god/sem)\']]')
            espb = driver.find_element(By.XPATH, '//h4[.//small[text() = \'Ukupan broj ESPB bodova\']]')

            curProg.duration = duration.text
            curProg.espb = espb.text

            for course in courseList:
                if "javascript" not in course.get_attribute("href"):
                    curCourse = Course()
                    print(course.get_attribute("href").split("/"))
                    curCourse.id = course.get_attribute("href").split("/")[3]
                    idArray = course.get_attribute("id").split("_")
                    curCourse.national_code = idArray[-2]+" - "+idArray[-1]
                    if courses.get(curCourse.national_code) == None:
                        curCourse.name = course.text.strip().strip("'")
                        print(course.text)
                        driver.execute_script("window.open('" + course.get_attribute("href") + "')")
                        driver.switch_to.window(driver.window_handles[-1])
                        time.sleep(3)
                        ppTabs2 = driver.find_element(By.XPATH, '//a[contains(@href, \'ppTabs-2\')]')
                        ppTabs2.click()

                        espbCourse = driver.find_element(By.XPATH, '//tr/td[text() = "ESPB"]/following-sibling::td')
                        goal = driver.find_element(By.XPATH, '//*[@id="ppTabs-2"]')
                        outcome = driver.find_element(By.XPATH, '//*[@id ="ppTabs-3"]')
                        courseSubjects = driver.find_element(By.XPATH, '//*[@id ="ppTabs-4"]')
                        curCourse.goal = goal.get_attribute("textContent")
                        curCourse.desired_outcome = outcome.get_attribute("textContent")
                        curCourse.espb = espbCourse.text
                        curProg.courses.append(curCourse)
                        courseSubjectsArray = courseSubjects.get_attribute("textContent").split(",")
                        for elem in courseSubjectsArray:
                            curCourse.subjects.append(elem.strip().strip("'").capitalize())
                        courses[curCourse.national_code] = curCourse
                        driver.execute_script("window.close()")
                        driver.switch_to.window(driver.window_handles[-1])

            driver.execute_script("window.close()")
            driver.switch_to.window(driver.window_handles[0])
            i = i + 1

    for programmeKey in programmes.keys():
        programmeElem = programmes.get(programmeKey)
        programmeOutput.append("<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/dušan/ontologies/2021/10/SEM_ONT#" + programmeElem.name + "\">\n")
        programmeOutput.append("\t<rdf:type rdf:resource=\"http://purl.org/vocab/aiiso/schema#Programme\"/>\n")
        for courseElem in programmeElem.courses:
            programmeOutput.append("\t<contains_Course rdf:resource=\"http://www.semanticweb.org/dušan/ontologies/2021/10/SEM_ONT#"+courseElem.name+"\"/>\n")
        programmeOutput.append("\t<duration>"+programmeElem.duration+"</duration>\n")
        programmeOutput.append("\t<espb>" + str(programmeElem.espb) + "</espb>\n")
        programmeOutput.append("\t<id>" + programmeElem.id + "</id>\n")
        programmeOutput.append("</owl:NamedIndividual>\n\n")

    for courseKey in courses.keys():
        courseElem = courses.get(courseKey)
        courseOutput.append(
            "<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/dušan/ontologies/2021/10/SEM_ONT#" + courseElem.name + "\">\n")
        courseOutput.append("\t<rdf:type rdf:resource=\"http://purl.org/vocab/aiiso/schema#Course\"/>\n")
        for subjElem in courseElem.subjects:
            courseOutput.append("\t<contains_Subject rdf:resource=\"http://www.semanticweb.org/dušan/ontologies/2021/10/SEM_ONT#"+subjElem.strip(">")+"\"/>\n")
            subjectOutput.append("<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/dušan/ontologies/2021/10/SEM_ONT#" + subjElem.strip(">") + "\">\n")
            subjectOutput.append("\t<rdf:type rdf:resource=\"http://purl.org/vocab/aiiso/schema#Subject\"/>\n")
            subjectOutput.append("</owl:NamedIndividual>\n\n")
        courseOutput.append("\t<goal>"+courseElem.goal+"</goal>\n")
        courseOutput.append("\t<national_code>" + courseElem.national_code + "</national_code>\n")
        courseOutput.append("\t<desired_outcome>" + courseElem.desired_outcome + "</desired_outcome>\n")
        courseOutput.append("\t<espb>" + str(courseElem.espb) + "</espb>\n")
        courseOutput.append("\t<id>" + courseElem.id + "</id>\n")
        courseOutput.append("</owl:NamedIndividual>\n\n")


    file = open("individuals.xml", "w", encoding="utf-8")
    for line in subjectOutput:
        file.write(line)
    for line in courseOutput:
        file.write(line)
    for line in programmeOutput:
        file.write(line)
    driver.close()
