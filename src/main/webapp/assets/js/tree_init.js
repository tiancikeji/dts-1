 var setting = {
        view: {
            showIcon: showIconForTree
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
    		onClick: zTreeOnClick
    	}
    };

    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };
    
    function zTreeOnClick(event, treeId, treeNode) {
    	var url = "http://localhost:8080/dts/monitor/"+ treeNode.id;
//        alert(treeNode.tId + ", " + treeNode.name +" ,"+ treeNode.id);
    	window.location.href = url;
    	
    };

    $.ajax( {  
        url : "http://localhost:8080/dts/settings/area.json",  
        type : "get",  
        dataType : "json",  
        success : init_tree
    });  
    
    function init_tree(data){
       	 $.fn.zTree.init($("#treeDemo"), setting,data );
    }
