	var setting = {
		view : {
			showIcon : showIconForTree
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};
	var zNodes = [ {
		id : 1,
		pId : 0,
		name : "厂区n",
		open : true
	}, {
		id : 11,
		pId : 1,
		name : "电缆沟n"
	}, {
		id : 111,
		pId : 11,
		name : "区段n"
	}, {
		id : 112,
		pId : 11,
		name : "区段n"
	}, {
		id : 113,
		pId : 11,
		name : "区段n"
	}, {
		id : 114,
		pId : 11,
		name : "区段n"
	}, {
		id : 12,
		pId : 1,
		name : "电缆沟w"
	}, {
		id : 121,
		pId : 12,
		name : "区段n"
	}, {
		id : 122,
		pId : 12,
		name : "区段n"
	}, {
		id : 123,
		pId : 12,
		name : "区段n"
	}, {
		id : 124,
		pId : 12,
		name : "区段n"
	}, {
		id : 13,
		pId : 1,
		name : "电缆沟n",
		isParent : true
	}, ];

	function showIconForTree(treeId, treeNode) {
		return !treeNode.isParent;
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});