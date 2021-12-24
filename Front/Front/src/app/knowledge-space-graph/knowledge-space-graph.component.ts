import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Id } from 'vis-data/declarations/data-interface';
import {Network, Edge, DataSet} from "vis-network/standalone";
import { Problem } from '../model/Problem';
import { KnowledgeSpaceService } from '../services/knowledge-space.service';

@Component({
  selector: 'app-knowledge-space-graph',
  templateUrl: './knowledge-space-graph.component.html',
  styleUrls: ['./knowledge-space-graph.component.css']
})
export class KnowledgeSpaceGraphComponent implements OnInit {
  name = "";
  selectedNode = 0;
  subjectId = 0;
  // create an array with nodes
  nodes = new DataSet([]);
  nodesReal = new DataSet([]);
  edgesArray: Edge[] = [];
  edgesArrayReal: Edge[] = [];
// create an array with edges
  edges = new DataSet(this.edgesArray);
  edgesReal = new DataSet(this.edgesArrayReal);
// create a network
  data = {
    nodes: this.nodes,
    edges: this.edges,
  };

  dataReal= {
    nodes: this.nodesReal,
    edges: this.edgesReal,
  };

  network: Network = null;
  networkReal: Network = null;

  constructor(private knowledgeSpaceService: KnowledgeSpaceService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      console.log(params);
      this.subjectId = +params.get("subjectId");
      this.drawRealNetwork();
      this.drawTheoreticalNetwork();
      
    });
  }

  drawTheoreticalNetwork(){
    this.knowledgeSpaceService.getSpacesForGraph(this.subjectId).subscribe(response =>{
      console.log(response);
      let problemSet = new Set<Problem>();

      response.forEach(element => {
        
        if(!element.realSpace){
          element.surmises.forEach(surmise => {
            surmise.problems.forEach(problemSurmise =>{
              this.edges.add([
                {from: surmise.problemFrom.id, to: problemSurmise.id}
              ])
              problemSet.add(problemSurmise);
            })
            problemSet.add(surmise.problemFrom);
          });
          
          problemSet.forEach(probElem => {
            if(!this.nodes.getIds().includes(probElem.id)){
              this.nodes.add([{
                id: probElem.id, label: probElem.name
              }])
          }
          });

        

      
      this.data = {
        nodes: this.nodes,
        edges: this.edges,
      };
      console.log(this.data);
      console.log(this.nodes);

      const options = {
        interaction: { hover: true },
        manipulation: {
          enabled: true,
          editNode: function (nodeData, callback){
            nodeData.label = (<HTMLInputElement>document.getElementById("labelvalue")).value;
            callback(nodeData);
  
          }
        },
        edges: {
          arrows: 'to',
          color: 'red'
        }
      };
      const container = document.getElementById("theoreticalspace");
      this.network = new Network(container, this.data, options);
      this.network.on("click", function (params) {
        params.event = "[original event]";
        console.log(
          "click event, getNodeAt returns: " + this.getNodeAt(params.pointer.DOM)
        );
        params.selectedNode = this.getNodeAt(params.pointer.DOM);
      });
      this.network.on("doubleClick", function (params) {
        params.event = "[original event]";
      });
      this.network.on("oncontext", function (params) {
        params.event = "[original event]";
      });
      this.network.on("dragStart", function (params) {
        // There's no point in displaying this event on screen, it gets immediately overwritten
        params.event = "[original event]";
      });
      this.network.on("dragging", function (params) {
        params.event = "[original event]";
      });
      this.network.on("dragEnd", function (params) {
        params.event = "[original event]";
        console.log("dragEnd Event:", params);
        console.log(
          "dragEnd event, getNodeAt returns: " + this.getNodeAt(params.pointer.DOM)
        );
      });
      this.network.on("controlNodeDragging", function (params) {
        params.event = "[original event]";
      });
      this.network.on("controlNodeDragEnd", function (params) {
        params.event = "[original event]";
        console.log("controlNodeDragEnd Event:", params);
      });
      this.network.on("zoom", function (params) {
      });
      this.network.on("showPopup", function (params) {
      });
      this.network.on("hidePopup", function () {
        console.log("hidePopup Event");
      });
      this.network.on("select", function (params) {
        console.log("select Event:", params);
      });
      this.network.on("selectNode", function (params) {
        console.log("selectNode Event:", params);
      });
      this.network.on("selectEdge", function (params) {
        console.log("selectEdge Event:", params);
      });
      this.network.on("deselectNode", function (params) {
        console.log("deselectNode Event:", params);
      });
      this.network.on("deselectEdge", function (params) {
        console.log("deselectEdge Event:", params);
      });
      this.network.on("hoverNode", function (params) {
        console.log("hoverNode Event:", params);
      });
      this.network.on("hoverEdge", function (params) {
        console.log("hoverEdge Event:", params);
      });
      this.network.on("blurNode", function (params) {
        console.log("blurNode Event:", params);
      });
      this.network.on("blurEdge", function (params) {
        console.log("blurEdge Event:", params);
      });
        }
    });

    });
  }

  drawRealNetwork(){

    this.knowledgeSpaceService.getSpacesForGraph(this.subjectId).subscribe(response =>{
      console.log(response);
      let problemSet = new Set<Problem>();

      response.forEach(element => {
        
        if(element.realSpace){
          element.surmises.forEach(surmise => {
            surmise.problems.forEach(problemSurmise =>{
              this.edgesReal.add([
                {from: surmise.problemFrom.id, to: problemSurmise.id}
              ])
              problemSet.add(problemSurmise);
            })
            problemSet.add(surmise.problemFrom);
          });
          
          problemSet.forEach(probElem => {
            if(!this.nodesReal.getIds().includes(probElem.id)){
              this.nodesReal.add([{
                id: probElem.id, label: probElem.name
              }])
          }
          });

        }

    this.dataReal = {
      nodes: this.nodesReal,
      edges: this.edgesReal,
    }

    const optionsReal = {
      interaction: { hover: true },
      manipulation: {
        enabled: false,
      },
      edges: {
        arrows: 'to',
        color: 'red'
      }
    };

    const containerReal = document.getElementById("realspace");
    this.networkReal = new Network(containerReal, this.dataReal, optionsReal);

    this.networkReal.on("click", function (params) {
      params.event = "[original event]";
      console.log(
        "click event, getNodeAt returns: " + this.getNodeAt(params.pointer.DOM)
      );
      params.selectedNode = this.getNodeAt(params.pointer.DOM);
    });
    this.networkReal.on("doubleClick", function (params) {
      params.event = "[original event]";
    });
    this.networkReal.on("oncontext", function (params) {
      params.event = "[original event]";
    });
    this.networkReal.on("dragStart", function (params) {
      // There's no point in displaying this event on screen, it gets immediately overwritten
      params.event = "[original event]";
    });
    this.networkReal.on("dragging", function (params) {
      params.event = "[original event]";
    });
    this.networkReal.on("dragEnd", function (params) {
      params.event = "[original event]";
      console.log("dragEnd Event:", params);
      console.log(
        "dragEnd event, getNodeAt returns: " + this.getNodeAt(params.pointer.DOM)
      );
    });
    this.networkReal.on("controlNodeDragging", function (params) {
      params.event = "[original event]";
    });
    this.networkReal.on("controlNodeDragEnd", function (params) {
      params.event = "[original event]";
      console.log("controlNodeDragEnd Event:", params);
    });
    this.networkReal.on("zoom", function (params) {
    });
    this.networkReal.on("showPopup", function (params) {
    });
    this.networkReal.on("hidePopup", function () {
      console.log("hidePopup Event");
    });
    this.networkReal.on("select", function (params) {
      console.log("select Event:", params);
    });
    this.networkReal.on("selectNode", function (params) {
      console.log("selectNode Event:", params);
    });
    this.networkReal.on("selectEdge", function (params) {
      console.log("selectEdge Event:", params);
    });
    this.networkReal.on("deselectNode", function (params) {
      console.log("deselectNode Event:", params);
    });
    this.networkReal.on("deselectEdge", function (params) {
      console.log("deselectEdge Event:", params);
    });
    this.networkReal.on("hoverNode", function (params) {
      console.log("hoverNode Event:", params);
    });
    this.networkReal.on("hoverEdge", function (params) {
      console.log("hoverEdge Event:", params);
    });
    this.networkReal.on("blurNode", function (params) {
      console.log("blurNode Event:", params);
    });
    this.networkReal.on("blurEdge", function (params) {
      console.log("blurEdge Event:", params);
    });

  })
  });
}

  editNode(){
    console.log(this.nodes);
    console.log(this.edges);
    console.log(this.selectedNode);
    this.nodes.update({id: this.selectedNode, label: name});
    this.network.redraw();



  }

  save() {


      console.log(this.nodes.get());
      console.log(this.edges.get());
      console.log(this.selectedNode);
  
      const map = new Map;
      let i = 0;
      let nodes = []
      let edgeList = []
      this.nodes.get().forEach(node => {
        map.set(node.id, i);
        nodes.push({
          id: i,
          subjectId: this.subjectId,
          name: node.label,
          description: "desc"
        });
        i = i + 1;
      });
  
      this.edges.get().forEach(element => {
        edgeList.push({
          from: map.get(element.from),
          to: map.get(element.to)
        })
      });
  
      const request = {
  
        name: "testSpace",
        subjectId: this.subjectId,
        links: edgeList,
        problems: nodes
  
      }
  
      console.log(edgeList);
      console.log(nodes);
      console.log(this.subjectId);
      console.log(request);
      this.knowledgeSpaceService.saveSpace(request).subscribe(response =>{

        Swal.fire(
          'Created!',
          'Knowledge space successfully created.',
          'success'
        )


      });
  }

  logout(){

  }
}
