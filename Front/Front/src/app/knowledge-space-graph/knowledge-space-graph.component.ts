import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import {Network, Edge, DataSet} from "vis-network/standalone";
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

  edgesArray: Edge[] = [];

// create an array with edges
  edges = new DataSet(this.edgesArray);

// create a network
  data = {
    nodes: this.nodes,
    edges: this.edges,
  };

  network: Network = null;

  constructor(private knowledgeSpaceService: KnowledgeSpaceService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.drawNetwork();
    this.route.paramMap.subscribe(params => {
      console.log(params);
      this.subjectId = +params.get("subjectId");
    });
  }

  drawNetwork(){
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
    const container = document.getElementById("mynetwork");
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
